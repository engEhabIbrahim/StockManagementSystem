package eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Stock;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OldGetPermission {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Stock from;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User writer;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User stockAdmin;
    private Date date;
    private String note;
}
