package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.consumed;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedPutPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumedPutDetailsRepository extends JpaRepository<ConsumedPutDetails, Long> {
    List<ConsumedPutDetails> findAllByPermission(ConsumedPutPermission permission);
    List<ConsumedPutDetails> findAllByPermission_From(User user);
}
