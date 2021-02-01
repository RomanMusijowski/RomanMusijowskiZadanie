package com.zadanie.RomanMusijowskiZadanie.services;

import com.zadanie.RomanMusijowskiZadanie.models.RoleName;
import com.zadanie.RomanMusijowskiZadanie.models.User;
import com.zadanie.RomanMusijowskiZadanie.repos.UserRepository;
import com.zadanie.RomanMusijowskiZadanie.security.services.EncryptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final EncryptionService encryptionService;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found."));
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(encryptionService.encryptString(user.getPassword()));
        user.setRoles(Collections.singleton(roleService.getByName(RoleName.ROLE_USER)));
        return userRepository.save(user);
    }

    @Override
    public User saveAdmin(User user) {
        user.setPassword(encryptionService.encryptString(user.getPassword()));
        user.setRoles(Collections.singleton(roleService.getByName(RoleName.ROLE_ADMIN)));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found."));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
