package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainGetItems;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainPutItems;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.stocks.main.MainGetItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MainGetItemsService {
    private final MainGetItemsRepository repository;

    public void addItems(List<MainPutItems> items, MainGetPermission permission) {
        items.forEach(item -> repository.save(MainGetItems.builder().id(item.getId()).permission(permission).product(item.getProduct()).build()));
    }

    public List<MainGetItems> findAll() {
        return repository.findAll();
    }

    public List<MainGetItems> getItems(User user, Product product, Integer number) {
        List<MainGetItems> matchesUserAndProduct = repository.findAllByProductAndPermission_To(product, user);
        List<MainGetItems> items = matchesUserAndProduct.subList(0, number);
        repository.deleteAll(items);
        return items;
    }

    public Map<Product, Integer> getProductsMap(User user) {
        Map<Product, Integer> map = new HashMap<>();
        repository.findAllByPermission_To(user).forEach(item -> map.merge(item.getProduct(), 1, Integer::sum));
        return map;
    }


    public List<StockVolume> getRecords() {
        return repository.getRecords();
    }

    public List<User> getGivenUsers() {
        return repository.getGivenUsers();
    }

}
