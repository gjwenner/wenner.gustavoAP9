package com.mindhub.Homebanking.services.implement;

import com.mindhub.Homebanking.models.ClientLoan;
import com.mindhub.Homebanking.repositories.ClientLoanRepository;
import com.mindhub.Homebanking.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoanServiceImplement implements ClientLoanService {
    @Autowired
    ClientLoanRepository clientLoanRepository;

    @Override
    public void newClientLoan(ClientLoan clientLoan) {

        clientLoanRepository.save(clientLoan);

    }
}
