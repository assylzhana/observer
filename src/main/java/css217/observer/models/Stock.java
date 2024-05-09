package css217.observer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="stocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    private float price;
    @ManyToMany
    private List<Investor> investors;
    public void registerInvestor(Investor investor) {
        investors.add(investor);
    }

    public void unregisterInvestor(Investor investor) {
        investors.remove(investor);
    }

    public void notifyInvestors() {
        for (Investor investor : investors) {
            investor.update(this);
        }
    }

    public void updatePrice(float price) {
        this.price = price;
        notifyInvestors();
    }
}
