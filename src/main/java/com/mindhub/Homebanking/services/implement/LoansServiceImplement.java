package com.mindhub.Homebanking.services.implement;

import com.mindhub.Homebanking.dtos.ClientDTO;
import com.mindhub.Homebanking.dtos.LoanDTO;
import com.mindhub.Homebanking.models.Loan;
import com.mindhub.Homebanking.repositories.LoanRepository;
import com.mindhub.Homebanking.services.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoansServiceImplement implements LoansService {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public List<LoanDTO> getLoanDTO() {
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(Collectors.toList());

    }

    @Override
    public Loan findById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public LoanDTO getLoanDto(Long id) {
        return new LoanDTO(this.findById(id));
    }

    @Override
    public void newLoan(Loan loan) {
        loanRepository.save(loan);

    }
}
