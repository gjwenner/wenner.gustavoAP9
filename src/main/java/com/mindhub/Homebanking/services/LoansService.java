package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.dtos.LoanDTO;
import com.mindhub.Homebanking.models.Loan;


import java.util.List;

public interface LoansService {

    List<LoanDTO> getLoanDTO();

    Loan findById(Long id);

    LoanDTO getLoanDto(Long id);

    void newLoan(Loan loan);

}
