package css217.observer.controllers;

import css217.observer.models.Stock;
import css217.observer.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("view")
    public List<Stock> getStocks(){
        return stockService.getStocks();
    }
    @PostMapping
    public void addStock(@RequestBody Stock stock) {
        stockService.addStock(stock);
    }
    @PutMapping
    public Stock edit(@RequestBody Stock stock){
        return stockService.editStock(stock);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        stockService.deleteStockById(id);
    }
}
