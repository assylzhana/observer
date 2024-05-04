package css217.observer.services;

import css217.observer.models.Investor;
import css217.observer.repositories.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InvestorService implements UserDetailsService {
    @Autowired
    private InvestorRepository investorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return investorRepository.findByEmail(email);
    }

    public void save(Investor investor) {
        investorRepository.save(investor);
    }

    public void addInvestor(Investor investor) {
        String encodedPassword = passwordEncoder.encode(investor.getPassword());
        investor.setPassword(encodedPassword);
        investorRepository.save(investor);
    }

}
