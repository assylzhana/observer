package css217.observer.controllers;

import css217.observer.models.Investor;
import css217.observer.models.Role;
import css217.observer.services.InvestorService;
import css217.observer.services.RoleService;
import css217.observer.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private InvestorService investorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;
    @Autowired
    private StockService stockService;
    @PreAuthorize("isAnonymous()")
    @GetMapping("/sign-up")
    private String signUp(){
        return "sign-up";
    }
    @PreAuthorize("isAnonymous()")
    @PostMapping("/sign-up")
    public String signUp(Investor investor) {

        investorService.addInvestor(investor);
        return "redirect:/profile";
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping("/sign-in")
    private String signIn(){
        return "sign-in";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    private String profile(){
        return "profile";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/stocks")
    private String stocks(Model model){
        model.addAttribute("stocks", stockService.getStocks());
        return "stocks";
    }
}
