package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.consumed.ConsumedGetDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumedGetDetailsService {
    private final ConsumedGetDetailsRepository repository;

    public ConsumedGetDetails add(ConsumedGetDetails details) {
        return repository.save(details);
    }

    public List<ConsumedGetDetails> findAll() {
        return repository.findAll();
    }

    public List<ConsumedGetDetails> findAllByPermission(ConsumedGetPermission permission) {
        return repository.findAllByPermission(permission);
    }

    public List<ConsumedGetDetails> findAllByPermission_To(User user) {
        return repository.findAllByPermission_To(user);
    }
}
