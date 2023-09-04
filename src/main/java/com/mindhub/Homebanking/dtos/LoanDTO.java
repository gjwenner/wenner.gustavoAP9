package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.Loan;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoanDTO {

    private Long id;
    private String name;
    private Double maxAmount;

    @ElementCollection
        private List<Integer> payments = new ArrayList<>();

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getPayments();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }
}
