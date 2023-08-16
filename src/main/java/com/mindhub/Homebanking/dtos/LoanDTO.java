package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.Loan;

import java.util.HashSet;
import java.util.Set;

public class LoanDTO {

    private Long id;
    private String name;
    private Double maxAmount;

    private Set<ClientLoanDTO> clientLoan = new HashSet<>();

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
    }

    public Long getId() {
        return id;
    }
}
