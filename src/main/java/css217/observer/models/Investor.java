package css217.observer.models;

import css217.observer.observer.Observer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="investors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investor implements UserDetails, Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private List<Stock> stocks;
    @ManyToOne
    private Role role;
    private String email;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) role;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void update(Stock stock) {
        System.out.println("Notified " + name + " of price update: " + stock.getSymbol() + " - $" + stock.getPrice());
        float stockPrice = stock.getPrice();
        if (stockPrice < 50) {
            System.out.println("Buying more " + stock.getSymbol() + " stocks!");
        } else if (stockPrice > 100) {
            System.out.println("Selling some " + stock.getSymbol() + " stocks!");
        } else {
            System.out.println("Observing " + stock.getSymbol() + " for now.");
        }
    }

}
