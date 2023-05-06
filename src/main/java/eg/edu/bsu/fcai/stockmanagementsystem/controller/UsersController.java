package eg.edu.bsu.fcai.stockmanagementsystem.controller;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.Form;
import eg.edu.bsu.fcai.stockmanagementsystem.assets.UserForm;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.Stock;
import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.service.HistoryService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.UpdateUserService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.StocksService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static eg.edu.bsu.fcai.stockmanagementsystem.assets.ExceptionsMessagesRepository.USER_IN_USE;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UsersController {
    private final UpdateUserService updateUserService;
    private final UserService userService;
    private final StocksService stocksService;
    private final HistoryService historyService;

    @GetMapping
    public String getAllUsersPage(Model model) {
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("enabledUsers", userService.findAllEnabledUsers());
        model.addAttribute("disabledUsers", userService.findAllDisabledUsers());
        return "/users";
    }

    private User getPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping(value = "/{id}")
    public String getEditUserPage(@PathVariable(name = "id") String id, Model model) {
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("action", "/users");
        return "/profile-edit";
    }

    @PostMapping
    public String updateUserInfo(@ModelAttribute UserForm form, Model model) {
        try {
            updateUserService.updateUser(form, false);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return getEditUserPage(form.id(), model);
        }
        return "redirect:/users";
    }


    @GetMapping(value = "/delete/{id}")
    public String deleteUserById(@PathVariable(name = "id") String id, Model model) {
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("error", USER_IN_USE);
            return getAllUsersPage(model);
        }
        return "redirect:/users";
    }

    @GetMapping(value = "/enable/{id}")
    public String enableUser(@PathVariable(name = "id") String id) {
        User user = userService.findById(id.strip());
        user.setEnabled(true);
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/disable/{id}")
    public String disableUser(@PathVariable(name = "id") String id) {
        User user = userService.findById(id.strip());
        user.setEnabled(false);
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/usage/{id}")
    public String getUserUsage(@PathVariable(name = "id") String id, Model model) {
        historyService.getHistoryForUser(model, userService.findById(id));
        model.addAttribute("currentUser", getPrincipal());
        return "/history";
    }
    @GetMapping(value = "/set/admin")
    public String getSetStockAdminPage(Model model) {
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("stocks", stocksService.findAll());
        return "/operation/set-stock-admin";
    }

    @PostMapping(value = "/set/admin")
    public String getSetStockAdminPage(@ModelAttribute Form form, Model model) {
        Stock stock;
        try {
            stock = stocksService.findById(form.getStockId());
        } catch (Exception e) {
            return getSetStockAdminPage(model);
        }
        User user = userService.findById(form.getUserId());
        stock.setAdmin(user);
        userService.add(user);
        return "redirect:/users";
    }
}
