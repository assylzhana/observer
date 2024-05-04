package css217.observer.controllers;

import css217.observer.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private StockService stockService;
    @GetMapping("/sign-up")
    private String signUp(){
        return "sign-up";
    }
    @GetMapping("/sign-in")
    private String signIn(){
        return "sign-in";
    }
    @GetMapping("/profile")
    private String profile(){
        return "profile";
    }
    @GetMapping("/stocks")
    private String stocks(Model model){
        model.addAttribute("stocks", stockService.getStocks());
        return "stocks";
    }
}
