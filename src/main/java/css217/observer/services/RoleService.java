package css217.observer.services;

import css217.observer.models.Role;
import css217.observer.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role getDefaultRole() {
        return new Role("USER");
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
