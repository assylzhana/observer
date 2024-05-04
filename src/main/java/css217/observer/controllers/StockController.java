package css217.observer.controllers;

import css217.observer.models.Stock;
import css217.observer.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("view")
    public List<Stock> getStocks(){
        return stockService.getStocks();
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public void addStock(@RequestBody Stock stock) {
        stockService.addStock(stock);
    }
    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public Stock edit(@RequestBody Stock stock){
        return stockService.editStock(stock);
    }
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        stockService.deleteStockById(id);
    }
}
