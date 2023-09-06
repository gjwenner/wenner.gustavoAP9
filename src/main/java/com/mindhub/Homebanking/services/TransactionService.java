package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.dtos.TransactionDTO;
import com.mindhub.Homebanking.models.Transaction;


import java.util.List;

public interface TransactionService {

    List<TransactionDTO> getTransactionDTO();

    Transaction findById(Long id);

    TransactionDTO getTransactionDTO(Long id);

    void newTransaction(Transaction transaction);
}
