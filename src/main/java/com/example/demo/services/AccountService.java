package com.example.demo.services;

import com.example.demo.Model.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public void save(Account account){
        accountRepository.save(account);
    }
}
