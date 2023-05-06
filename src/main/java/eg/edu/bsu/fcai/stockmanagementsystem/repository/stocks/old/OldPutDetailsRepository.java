package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.old;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Type;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldPutPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldPutDetailsRepository extends JpaRepository<OldPutDetails, Long> {
    List<OldPutDetails> findByPermission_Type(Type type);

    List<OldPutDetails> findByPermission(OldPutPermission permission);

    List<OldPutDetails> findByPermission_From(User user);
}
