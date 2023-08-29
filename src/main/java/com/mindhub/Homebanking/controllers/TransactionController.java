package com.mindhub.Homebanking.controllers;

import com.mindhub.Homebanking.dtos.TransactionDTO;
import com.mindhub.Homebanking.models.Transaction;
import com.mindhub.Homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(){
        List<Transaction> allTransactions = transactionRepository.findAll();

        List<TransactionDTO> convertedList;
        convertedList = allTransactions
                .stream()
                .map(currenttransaction -> new TransactionDTO(currenttransaction))
                .collect(Collectors.toList());

        return convertedList;
    }

    @GetMapping("/transactions/{id}")
    public TransactionDTO getTransactionsById(@PathVariable Long id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return new TransactionDTO(transaction.get());
    }



}
