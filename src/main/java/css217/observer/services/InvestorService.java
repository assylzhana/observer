package css217.observer.services;

import css217.observer.models.Investor;
import css217.observer.repositories.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestorService {
    @Autowired
    private InvestorRepository investorRepository;
}
