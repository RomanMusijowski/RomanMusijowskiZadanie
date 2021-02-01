package com.zadanie.RomanMusijowskiZadanie.security;

import com.zadanie.RomanMusijowskiZadanie.models.Role;
import com.zadanie.RomanMusijowskiZadanie.models.RoleName;
import com.zadanie.RomanMusijowskiZadanie.models.User;
import com.zadanie.RomanMusijowskiZadanie.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByUsername(s);

        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(Role::getName)
                .map(RoleName::toString)
                .map(str -> new SimpleGrantedAuthority(str))
                .collect(Collectors.toSet());

        return buildUserForAuthentication(user, authorities);
    }

    private UserDetails buildUserForAuthentication(User user, Set<SimpleGrantedAuthority> authorities) {
        return new UserDetailsImpl(
                authorities,
                user.getUsername(),
                user.getPassword(),
                user.getActive());
    }
}
