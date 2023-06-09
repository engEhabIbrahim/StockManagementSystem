package eg.edu.bsu.fcai.stockmanagementsystem.repository.entities;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByStock(Stock stock);
}
