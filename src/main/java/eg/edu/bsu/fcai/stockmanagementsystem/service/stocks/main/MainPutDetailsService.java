package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.main.MainPutDetailsRepository;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPutDetailsService {
    private final MainPutDetailsRepository repository;
    private final TypeService typeService;

    public MainPutDetails add(MainPutDetails details) {
        return repository.save(details);
    }

    public List<MainPutDetails> findAll() {
        return repository.findAll();
    }

    public List<MainPutDetails> findByPermission(MainPutPermission permission) {
        return repository.findByPermission(permission);
    }

    public List<MainPutDetails> findAllNewAdditionsByUser(User user) {
        return repository.findAllByUserAndNotPermission_Type(user, typeService.findById(4L));
    }

    public List<MainPutDetails> findAllReturnedAdditionsByUser(User user) {
        return repository.findAllByPermission_FromAndPermission_Type(user, typeService.findById(4L));
    }

    public List<MainPutDetails> findAllReturns() {
        return repository.findAllByPermission_Type(typeService.findById(4L));
    }

    public List<MainPutDetails> findAllAdditions() {
        return repository.findAllByNotPermission_Type(typeService.findById(4L));
    }
}
