package eg.edu.bsu.fcai.stockmanagementsystem.repository.entities;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemStatusRepository extends JpaRepository<ItemStatus, Long> {
}
