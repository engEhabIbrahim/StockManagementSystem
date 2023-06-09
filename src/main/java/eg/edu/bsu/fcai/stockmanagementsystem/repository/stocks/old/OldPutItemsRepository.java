package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.old;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldPutItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldPutItemsRepository extends JpaRepository<OldPutItems, Long> {
    @Query(value = "" +
            "SELECT " +
            "new eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume(opi.product, COUNT(opi.id))" +
            "FROM OldPutItems opi " +
            "GROUP BY opi.product")
    List<StockVolume> getRecords();

    List<OldPutItems> findAllByProduct(Product product);
}
