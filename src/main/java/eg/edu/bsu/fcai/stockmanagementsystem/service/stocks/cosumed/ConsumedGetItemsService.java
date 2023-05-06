package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedGetItems;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedPutItems;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.consumed.ConsumedGetItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumedGetItemsService {
    private final ConsumedGetItemsRepository repository;

    public void addItems(List<ConsumedPutItems> items, ConsumedGetPermission permission) {
        items.forEach(item -> repository.save(ConsumedGetItems.builder().id(item.getId()).permission(permission).product(item.getProduct()).build()));
    }

    public List<ConsumedGetItems> findAll() {
        return repository.findAll();
    }

    public List<StockVolume> getRecords() {
        return repository.getRecords();
    }

}
