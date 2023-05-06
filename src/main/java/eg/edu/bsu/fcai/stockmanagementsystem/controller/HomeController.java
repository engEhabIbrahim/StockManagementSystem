package eg.edu.bsu.fcai.stockmanagementsystem.controller;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.ProductService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.StocksService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.UserService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainGetPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final StocksService stocksService;
    private final UserService userService;
    private final ProductService productService;
    private final MainPutPermissionService mainPutPermissionService;
    private final MainGetPermissionService mainGetPermissionService;

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("currentUser", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("users", userService.count());
        model.addAttribute("products", productService.count());
        model.addAttribute("stocks", stocksService.count());
        model.addAttribute("additions", mainPutPermissionService.count());
        model.addAttribute("takeouts", mainGetPermissionService.count());
        return "home";
    }
}
