package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainGetItems;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainPutItems;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.main.MainPutItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPutItemsService {
    private final MainPutItemsRepository repository;

    public void addItems(MainPutPermission permission, Product product, Integer amount) {
        while (amount -- > 0) {
            repository.save(MainPutItems.builder().permission(permission).product(product).build());
        }
    }

    public void addItems(List<MainGetItems> items, MainPutPermission permission) {
        items.forEach(item -> repository.save(MainPutItems.builder().id(item.getId()).product(item.getProduct()).permission(permission).build()));
    }

    public List<MainPutItems> getItems(Product product, Integer number) {
        List<MainPutItems> items = repository.findByProduct(product).subList(0, number);
        repository.deleteAll(items);
        return items;
    }

    public List<StockVolume> getRecords() {
        return repository.getRecords();
    }


}
