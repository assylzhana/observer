package css217.observer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="investors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investor {
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
}
