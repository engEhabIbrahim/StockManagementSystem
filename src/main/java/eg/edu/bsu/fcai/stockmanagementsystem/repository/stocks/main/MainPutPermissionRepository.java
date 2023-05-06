package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainPutPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainPutPermissionRepository extends JpaRepository<MainPutPermission, Long> {
}
