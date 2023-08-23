package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;
    private Integer payments;
    private Double amount;


    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan){
        this.id = clientLoan.getId();
        this.payments = clientLoan.getPayments();
        this.amount = clientLoan.getAmount();

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
