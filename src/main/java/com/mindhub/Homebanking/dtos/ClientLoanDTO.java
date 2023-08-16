package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;
    private Long loanid;
    private String name;
    private Integer payments;
    private Double amount;


    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan){
        this.id = clientLoan.getId();
        this.loanid = clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();
        this.payments = clientLoan.getPayments();
        this.amount = clientLoan.getAmount();

    }



    public Long getId() {
        return id;
    }

    public Long getLoan() {
        return loanid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLoanid(Long loanid) {
        this.loanid = loanid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }
}
