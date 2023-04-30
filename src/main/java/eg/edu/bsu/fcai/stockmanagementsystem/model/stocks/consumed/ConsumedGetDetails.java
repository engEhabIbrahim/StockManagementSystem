package eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.ItemStatus;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConsumedGetDetails {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ConsumedGetPermission permission;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ItemStatus status;
    private Integer number;
    private String note;
}
