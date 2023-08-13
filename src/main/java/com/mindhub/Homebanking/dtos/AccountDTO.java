package com.mindhub.Homebanking.dtos;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;


import com.mindhub.Homebanking.models.Account;
public class AccountDTO {
    private Long id;
    private String number;
    private LocalDate creationDate;
    private Double balance;

    private Set<TransactionDTO> transactions = new HashSet<>();

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
        this.transactions = account.getTransaction()
                .stream()
                .map(transaction -> new TransactionDTO(transaction))
                .collect(Collectors.toSet());

    }

    public Long getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public Double getBalance() {
        return balance;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }
}
