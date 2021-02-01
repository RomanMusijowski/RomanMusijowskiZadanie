package com.zadanie.RomanMusijowskiZadanie.security.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encryptString(String input) {
        return passwordEncoder.encode(input);
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {

        if (plainPassword.equals(null)){
            return false;
        }else {
            return passwordEncoder.matches(plainPassword, encryptedPassword);
        }
    }
}
