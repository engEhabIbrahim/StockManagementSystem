package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old;

import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.old.OldGetDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OldGetDetailsService {
    private final OldGetDetailsRepository repository;

    public OldGetDetails add(OldGetDetails permissionDetails) {
        return repository.save(permissionDetails);
    }

    public List<OldGetDetails> findAll() {
        return repository.findAll();
    }

    public List<OldGetDetails> findAllByPermission(OldGetPermission permission) {
        return repository.findAllByPermission(permission);
    }
}
