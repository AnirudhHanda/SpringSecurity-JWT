package com.walter.o21jwtproject.appuser.service;

import com.walter.o21jwtproject.appuser.model.UserPrincipal;
import com.walter.o21jwtproject.appuser.dao.UserRepo;
import com.walter.o21jwtproject.appuser.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username);

        // if user is found
        if (user != null) {
            return new UserPrincipal(user);
        } else {
            System.out.println("User 404");
            throw new UsernameNotFoundException("User not found");
        }
    }

    public AppUser saveUser(AppUser user) {

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepo.save(user);
    }
}
