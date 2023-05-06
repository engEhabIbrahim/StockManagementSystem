package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.consumed;

import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedGetPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumedGetPermissionsRepository extends JpaRepository<ConsumedGetPermission, Long> {
}
