package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.consumed.ConsumedPutDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumedPutDetailsService {
    private final ConsumedPutDetailsRepository repository;

    public ConsumedPutDetails add(ConsumedPutDetails details) {
        return repository.save(details);
    }

    public List<ConsumedPutDetails> findAll() {
        return repository.findAll();
    }

    public List<ConsumedPutDetails> findAllByPermission(ConsumedPutPermission permission) {
        return repository.findAllByPermission(permission);
    }

    public List<ConsumedPutDetails> findAllByPermission_From(User user) {
        return repository.findAllByPermission_From(user);
    }

}
