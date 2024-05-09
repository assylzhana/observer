package css217.observer.services;

import css217.observer.models.Investor;
import css217.observer.models.Role;
import css217.observer.repositories.InvestorRepository;
import css217.observer.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class InvestorService implements UserDetailsService {
    @Autowired
    private InvestorRepository investorRepository;
    @Autowired
    private RoleRepository roleRepository;
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
        investor.setRole(roleRepository.findByName("USER"));
        investorRepository.save(investor);
    }
    public Investor getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken || authentication == null) {
            return null;
        }
        String email = authentication.getName();
        return (Investor) investorRepository.findByEmail(email);
    }

}
