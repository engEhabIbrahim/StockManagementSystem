package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.consumed;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedPutItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumedPutItemsRepository extends JpaRepository<ConsumedPutItems, Long> {
    @Query(value = "" +
            "SELECT " +
            "new eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume(cpi.product, COUNT(cpi.id))" +
            "FROM ConsumedPutItems cpi " +
            "GROUP BY cpi.product")
    List<StockVolume> getRecords();

    List<ConsumedPutItems> findByProduct(Product product);
}
