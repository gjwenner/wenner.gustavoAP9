package com.mindhub.Homebanking.services.implement;

import com.mindhub.Homebanking.dtos.TransactionDTO;
import com.mindhub.Homebanking.models.Transaction;
import com.mindhub.Homebanking.repositories.TransactionRepository;
import com.mindhub.Homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImplement implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public List<TransactionDTO> getTransactionDTO() {
        return transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    @Override
    public Transaction findById(Long id) {
        return null;
    }

    @Override
    public TransactionDTO getTransactionDTO(Long id) {
        return null;
    }

    @Override
    public void newTransaction(Transaction transaction) {

    }
}
