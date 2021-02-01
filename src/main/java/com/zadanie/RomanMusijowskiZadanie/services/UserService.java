package com.zadanie.RomanMusijowskiZadanie.services;

import com.zadanie.RomanMusijowskiZadanie.models.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    User saveUser(User user);

    User saveAdmin(User user);

    List<User> findAll();

    User findById(Long id);

    void deleteById(Long id);
}
