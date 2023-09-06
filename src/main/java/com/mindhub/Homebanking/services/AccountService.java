package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.dtos.AccountDTO;
import com.mindhub.Homebanking.models.Account;

import java.util.List;

public interface AccountService {

    List<AccountDTO> getAccountsDTO();

    Account findById(Long id);

    //List<AccountDTO> getAccountDTO();

    AccountDTO getAccountDTO(Long id);

    Account findByNumber(String number);

    void newAccount(Account account);
}
