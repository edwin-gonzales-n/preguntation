package com.preguntation.services;

import com.preguntation.models.UserWithRoles;
import com.preguntation.models.user;
import com.preguntation.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {

    private final UsersRepository users;

    public UserDetailsLoader(UsersRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        user username = users.findByEmail(email);
        if (username == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found!", email));
        }
        return new UserWithRoles(username);
    }
}

