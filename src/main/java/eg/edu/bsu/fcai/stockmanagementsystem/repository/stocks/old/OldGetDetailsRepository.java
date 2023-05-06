package eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.old;

import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldGetPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldGetDetailsRepository extends JpaRepository<OldGetDetails, Long> {
    List<OldGetDetails> findAllByPermission(OldGetPermission permission);
}
