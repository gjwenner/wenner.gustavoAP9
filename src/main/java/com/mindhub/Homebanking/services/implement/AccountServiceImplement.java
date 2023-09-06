package com.mindhub.Homebanking.services.implement;

import com.mindhub.Homebanking.dtos.AccountDTO;
import com.mindhub.Homebanking.dtos.ClientDTO;
import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.services.AccountService;
import com.mindhub.Homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class AccountServiceImplement implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    //@Autowired
    //ClientService clientService;


    @Override
    public List<AccountDTO> getAccountsDTO() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    //@Override
    //public List<AccountDTO> getAccountDTO() {
      //  ClientDTO client = this.clientService.getCurrent(authentication.getName());
        //return client.getAccounts().stream().map(AccountDTO::new).collect(toList());
    //}

    @Override
    public AccountDTO getAccountDTO(Long id) {
        return new AccountDTO(this.findById(id));
    }

    @Override
    public Account findByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public void newAccount(Account account) {
        accountRepository.save(account);
    }
}
