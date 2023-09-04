package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;
    private Long loanId;

    private String name;
    private Integer payments;
    private Double amount;


    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan){
        this.id = clientLoan.getId();
        this.loanId = clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();
        this.payments = clientLoan.getPayments();
        this.amount = clientLoan.getAmount();

    }

    public Long getId() {
        return id;
    }
    public Long getLoanId() {
        return loanId;
    }
    public String getName() {
        return name;
    }
    public Integer getPayments() {
        return payments;
    }
    public Double getAmount() {
        return amount;
    }

}
