package css217.observer.services;

import css217.observer.models.Stock;
import css217.observer.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public void addStock(Stock stock) {
        stockRepository.save(stock);
    }

    public Stock editStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public void deleteStockById(Long id) {
        stockRepository.deleteById(id);
    }

    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }
}
