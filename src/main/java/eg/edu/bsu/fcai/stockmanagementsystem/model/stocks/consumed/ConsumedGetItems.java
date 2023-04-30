package eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConsumedGetItems {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ConsumedGetPermission permission;
}
