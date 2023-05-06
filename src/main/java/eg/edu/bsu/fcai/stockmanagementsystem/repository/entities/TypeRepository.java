package eg.edu.bsu.fcai.stockmanagementsystem.repository.entities;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
