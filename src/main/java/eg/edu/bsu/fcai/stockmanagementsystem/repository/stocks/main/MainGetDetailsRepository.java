package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainGetPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainGetDetailsRepository extends JpaRepository<MainGetDetails, Long> {
    List<MainGetDetails> findAllByPermission(MainGetPermission permission);
    List<MainGetDetails> findAllByPermission_To(User user);
}
