package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.bezkoder.spring.security.postgresql.models.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String username);

}
