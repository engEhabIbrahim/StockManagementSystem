package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.consumed;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedGetPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumedGetDetailsRepository extends JpaRepository<ConsumedGetDetails, Long> {
    List<ConsumedGetDetails> findAllByPermission(ConsumedGetPermission permission);
    List<ConsumedGetDetails> findAllByPermission_To(User user);

}
