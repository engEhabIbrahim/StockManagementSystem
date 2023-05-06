package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.old.OldPutDetailsRepository;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OldPutDetailsService {
    private final OldPutDetailsRepository repository;
    private final TypeService documentService;

    public OldPutDetails add(OldPutDetails details) {
        return repository.save(details);
    }

    public List<OldPutDetails> findAllReturns() {
        return repository.findByPermission_Type(documentService.findById(4L));
    }

    public List<OldPutDetails> findByPermission(OldPutPermission permission) {
        return repository.findByPermission(permission);
    }

    public List<OldPutDetails> findByPermission_From(User user) {
        return repository.findByPermission_From(user);
    }
}
