package eg.edu.bsu.fcai.stockmanagementsystem.service;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.consumed.ConsumedPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.main.MainPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.stocks.old.OldPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldPutDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final MainPutDetailsService mainPutDetailsService;
    private final MainGetDetailsService mainGetDetailsService;
    private final OldPutDetailsService oldPutDetailsService;
    private final OldGetDetailsService oldGetDetailsService;
    private final ConsumedPutDetailsService consumedPutDetailsService;
    private final ConsumedGetDetailsService consumedGetDetailsService;

    public void getHistoryForUser(Model model, User user) {
        List<MainPutDetails> mainPuts = mainPutDetailsService.findAllNewAdditionsByUser(user);
        if (mainPuts.size() > 0) {
            model.addAttribute("mainPuts", mainPuts);
        }
        List<MainGetDetails> mainGets = mainGetDetailsService.findAllByUser(user);
        if (mainGets.size() > 0) {
            model.addAttribute("mainGets", mainGets);
        }
        List<MainPutDetails> mainReturns = mainPutDetailsService.findAllReturnedAdditionsByUser(user);
        if (mainReturns.size() > 0) {
            model.addAttribute("mainReturns", mainReturns);
        }
        List<OldPutDetails> oldReturns = oldPutDetailsService.findByPermission_From(user);
        if (oldReturns.size() > 0) {
            model.addAttribute("oldReturns", oldReturns);
        }
        List<ConsumedPutDetails> consumedPuts = consumedPutDetailsService.findAllByPermission_From(user);
        if (consumedPuts.size() > 0) {
            model.addAttribute("consumedPuts", consumedPuts);
        }
        List<ConsumedGetDetails> consumedGets = consumedGetDetailsService.findAllByPermission_To(user);
        if (consumedGets.size() > 0) {
            model.addAttribute("consumedGets", consumedGets);
        }
    }

    public void getConsumedStockGets(Model model) {
        List<ConsumedGetDetails> consumedTakeouts = consumedGetDetailsService.findAll();
        if (consumedTakeouts.size() > 0) {
            model.addAttribute("consumedGets", consumedTakeouts);
        }
    }

    public void getConsumedStockPuts(Model model) {
        List<ConsumedPutDetails> consumedAdditions = consumedPutDetailsService.findAll();
        if (consumedAdditions.size() > 0) {
            model.addAttribute("consumedPuts", consumedAdditions);
        }
    }

    public void getOldStockReturns(Model model) {
        List<OldPutDetails> oldReturns = oldPutDetailsService.findAllReturns();
        if (oldReturns.size() > 0) {
            model.addAttribute("oldReturns", oldReturns);
        }
    }

    public void getMainStockReturns(Model model) {
        List<MainPutDetails> mainReturns = mainPutDetailsService.findAllReturns();
        if (mainReturns.size() > 0) {
            model.addAttribute("mainReturns", mainReturns);
        }
    }

    public void getMainStockGets(Model model) {
        List<MainGetDetails> takeouts = mainGetDetailsService.findAll();
        if (takeouts.size() > 0) {
            model.addAttribute("mainGets", takeouts);
        }
    }

    public void getMainStockPuts(Model model) {
        List<MainPutDetails> additions = mainPutDetailsService.findAllAdditions();
        if (additions.size() > 0) {
            model.addAttribute("mainPuts", additions);
        }
    }

    public void getOldExtractions(Model model) {
        List<OldGetDetails> oldGets = oldGetDetailsService.findAll();
        if (oldGets.size() > 0) {
            model.addAttribute("oldGets", oldGets);
        }
    }
}
