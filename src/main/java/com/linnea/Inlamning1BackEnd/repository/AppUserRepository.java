package com.linnea.Inlamning1BackEnd.repository;

import com.linnea.Inlamning1BackEnd.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}
