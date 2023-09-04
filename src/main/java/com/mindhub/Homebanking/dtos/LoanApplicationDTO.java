package com.mindhub.Homebanking.dtos;

public class LoanApplicationDTO {
    private Long loanTypeId;
    private Double amount;
    private Integer payments;
    private String toAccountNumber;

    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO(Long loanTypeId, Double amount, Integer payments,  String toAccountNumber) {
        this.loanTypeId = loanTypeId;
        this.amount = amount;
        this.payments = payments;
        this.toAccountNumber = toAccountNumber;
    }

    public Long getLoanTypeId() {
        return loanTypeId;
    }
    public Integer getPayments() {
        return payments;
    }
    public Double getAmount() {
        return amount;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }
}
