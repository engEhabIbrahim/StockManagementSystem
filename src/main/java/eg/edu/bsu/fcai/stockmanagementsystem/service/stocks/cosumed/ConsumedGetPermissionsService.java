package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed;

import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.consumed.ConsumedGetPermissionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumedGetPermissionsService {
    private final ConsumedGetPermissionsRepository repository;

    public ConsumedGetPermission add(ConsumedGetPermission permissions) {
        return repository.save(permissions);
    }

    public ConsumedGetPermission findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
