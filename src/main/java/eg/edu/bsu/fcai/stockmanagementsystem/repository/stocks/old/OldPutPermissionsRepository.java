package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.old;

import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldPutPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OldPutPermissionsRepository extends JpaRepository<OldPutPermission, Long> {
}
