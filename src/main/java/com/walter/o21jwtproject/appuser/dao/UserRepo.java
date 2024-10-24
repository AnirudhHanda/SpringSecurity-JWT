package com.walter.o21jwtproject.appuser.dao;

import com.walter.o21jwtproject.appuser.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Integer> {
    AppUser findByUsername(String username);
}
