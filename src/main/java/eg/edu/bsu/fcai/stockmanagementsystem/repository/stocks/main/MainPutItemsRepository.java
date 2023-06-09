package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainPutItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainPutItemsRepository extends JpaRepository<MainPutItems, Long> {
    @Query(value = "" +
            "SELECT " +
            "new eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume(msi.product, COUNT(msi.id))" +
            "FROM MainPutItems msi " +
            "GROUP BY msi.product")
    List<StockVolume> getRecords();

    List<MainPutItems> findByProduct(Product product);
}
