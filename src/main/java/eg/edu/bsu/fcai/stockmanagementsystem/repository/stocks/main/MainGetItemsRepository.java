package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainGetItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainGetItemsRepository extends JpaRepository<MainGetItems, Long> {
    @Query(value = "" +
            "SELECT " +
            "new eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume(mgi.product, COUNT(mgi.id))" +
            "FROM MainGetItems mgi " +
            "GROUP BY mgi.product")
    List<StockVolume> getRecords();

    @Query(value = "SELECT mgp.to " +
            "FROM MainGetPermission mgp, MainGetItems mgi " +
            "WHERE mgi.permission=mgp " +
            "GROUP BY mgp.to")
    List<User> getGivenUsers();

    List<MainGetItems> findAllByProductAndPermission_To(Product product, User user);

    List<MainGetItems> findAllByPermission_To(@Param("user") User user);
}
