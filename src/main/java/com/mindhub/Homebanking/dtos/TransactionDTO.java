package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.Transaction;
import com.mindhub.Homebanking.models.TransactionType;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    private TransactionType type;
    private Double amount;
    private String descriptions;
    private LocalDate date;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.descriptions = transaction.getDescriptions();
        this.date = transaction.getDate();
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public LocalDate getDate() {
        return date;
    }
}