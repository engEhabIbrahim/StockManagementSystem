package eg.edu.bsu.fcai.stockmanagementsystem.controller.operation;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.Form;
import eg.edu.bsu.fcai.stockmanagementsystem.assets.FormService;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Stock;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.ItemStatusService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.ProductService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.StocksService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.UserService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainGetItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldPutItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldPutPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/permission/return")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN', 'USER')")
public class ReturnPermissionController {
    private final UserService userService;
    private final FormService formService;
    private final StocksService stocksService;
    private final ProductService productService;
    private final ItemStatusService itemStatusService;
    private final MainPutPermissionService mainPutPermissionService;
    private final MainPutItemsService mainPutItemsService;
    private final MainPutDetailsService mainPutDetailsService;
    private final MainGetItemsService mainGetItemsService;
    private final OldPutItemsService oldPutItemsService;
    private final OldPutDetailsService oldPutDetailsService;
    private final OldPutPermissionService oldPutPermissionService;
    private final List<Form> details = new ArrayList<>();
    private Map<Product, Integer> items;
    private User user;
    private Stock stock;

    @GetMapping(value = "/select-user-and-stock")
    public String getSelectUserAndStockPage(Model model) {
        model.addAttribute("users", mainGetItemsService.getGivenUsers());
        model.addAttribute("stocks", stocksService.findAll().subList(0, 2));
        model.addAttribute("currentUser", getPrincipal());
        return "/operation/select-user-and-stock";
    }

    @PostMapping(value = "/select-user-and-stock")
    public String postUserAndStock(@ModelAttribute Form form) {
        user = userService.findById(form.getUserId());
        stock = stocksService.findById(form.getStockId());
        items = mainGetItemsService.getProductsMap(user);
        details.clear();
        return "redirect:/permission/return/new";
    }

    @GetMapping(value = "/new")
    public String getNewReturnPermissionPage(Model model) {
        if (user == null || stock == null) {
            return "redirect:/permission/return/select-user-and-stock";
        }
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("user", user);
        model.addAttribute("stock", stock);
        model.addAttribute("items", items);
        model.addAttribute("details", details);
        return "/operation/return-permission";
    }

    @PostMapping(value = "/new")
    public String postReturnPermission(@ModelAttribute Form form, Model model) {
        completeFormDetails(form);
        if (details.size() > 0) {
            if (stock.getId().equals(1L)) {
                MainPutPermission permission = mainPutPermissionService.add(formService.toMainPutPermissions(form));
                for (var detail : details) {
                    mainPutDetailsService.add(formService.toMainPutDetails(detail, permission));
                    mainPutItemsService.addItems(mainGetItemsService.getItems(user, detail.getProduct(), detail.getNumber()), permission);
                }
                user = null;
                stock = null;
                return "redirect:/stocks/main";
            } else if (stock.getId().equals(2L)) {
                OldPutPermission permission = oldPutPermissionService.add(formService.toOldPutPermissions(form));
                for (var detail : details) {
                    oldPutDetailsService.add(formService.toOldPutDetails(detail, permission));
                    oldPutItemsService.addItems(mainGetItemsService.getItems(user, detail.getProduct(), detail.getNumber()), permission);
                }
                user = null;
                stock = null;
                return "redirect:/stocks/old";
            }
        }
        model.addAttribute("error", "تفاصيل الأسترجاع فارغة");
        return getNewReturnPermissionPage(model);
    }

    private void completeFormDetails(Form form) {
        User currentUser = getPrincipal();
        form.setStockId(stock.getId());
        form.setUserId(user.getId());
        form.setWriterId(currentUser.getId());
        form.setDocumentId(4L);
    }

    @GetMapping(value = "/{id}")
    public String getReturnDetailsPage(@PathVariable(name = "id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("max", items.get(product));
        return "/operation/return-details";
    }

    @PostMapping(value = "/item/add")
    public String postReturnDetails(@ModelAttribute Form form) {
        Product product = productService.findById(form.getProductId());
        form.setProduct(product);
        form.setStatus(itemStatusService.findById(3L));
        items.merge(product, -form.getNumber(), Integer::sum);
        if (items.get(product) == 0) {
            items.remove(product);
        }
        details.add(form);
        return "redirect:/permission/return/new";
    }

    @GetMapping(value = "/item-delete/{index}")
    public String deleteReturnDetails(@PathVariable(name = "index") Integer index) {
        var det = details.get(index);
        Product product = det.getProduct();
        if (items.get(product) == 0) {
            items.put(product, det.getNumber());
        } else {
            items.merge(product, det.getNumber(), Integer::sum);
        }
        details.remove(det);
        return "redirect:/permission/return/new";
    }

    @GetMapping(value = "/new/clear")
    public String clearAndGetNewRetrievePermissionPage() {
        if (stock == null || user == null) {
            return "redirect:/permission/return/select-user-and-stock";
        }
        details.clear();
        user = null;
        stock = null;
        items = null;
        return "redirect:/permission/return/get-user";
    }

    private User getPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
