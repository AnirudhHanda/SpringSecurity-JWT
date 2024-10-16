package com.walter.o21jwtproject.registration;

import com.walter.o21jwtproject.appuser.model.AppUser;
import com.walter.o21jwtproject.appuser.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody AppUser user) {
        AppUser savedUser = myUserDetailsService.saveUser(user);

        if(savedUser != null) {
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
