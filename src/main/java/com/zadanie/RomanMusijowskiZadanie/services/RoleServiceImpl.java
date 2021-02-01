package com.zadanie.RomanMusijowskiZadanie.services;

import com.zadanie.RomanMusijowskiZadanie.models.Role;
import com.zadanie.RomanMusijowskiZadanie.models.RoleName;
import com.zadanie.RomanMusijowskiZadanie.repos.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
