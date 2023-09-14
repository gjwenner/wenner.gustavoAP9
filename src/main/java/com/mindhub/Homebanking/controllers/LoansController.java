package com.mindhub.Homebanking.controllers;


import com.mindhub.Homebanking.dtos.LoanApplicationDTO;
import com.mindhub.Homebanking.dtos.LoanDTO;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.*;
import com.mindhub.Homebanking.services.*;
import org.objectweb.asm.Opcodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mindhub.Homebanking.models.TransactionType.CREDIT;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class LoansController {
    @Autowired
    private LoansService loansService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientLoanService clientLoanService;

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/loans")
    public List<LoanDTO> getLoan(){
        return loansService.getLoanDTO();
    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> createLoans(@RequestBody LoanApplicationDTO loanApplicationDTO,
                                              Authentication authentication){
    //Obtener información del cliente autenticado
        Client current = clientService.getCurrent(authentication.getName());
        Account creditAccount = accountService.findByNumber(loanApplicationDTO.getToAccountNumber());
        Loan loanApply = loansService.findById(loanApplicationDTO.getLoanId());
        List <Integer> payments = loanApply.getPayments();

    //Comprobar que el prestamo exista
        if(loanApply == null){
            return new ResponseEntity<>("El prestamo Solicitado no existe", HttpStatus.FORBIDDEN);
        }

    //Comprobar que los parametros no sean nulos
        if(loanApplicationDTO.getAmount() <= 0 || loanApplicationDTO.getPayments()<= 0 || loanApplicationDTO.getToAccountNumber().isEmpty()){
            return new ResponseEntity<>("los datos no pueden estar vacios", HttpStatus.FORBIDDEN);
        }
    // Comprobar que la cuenta de destino existe
        if(accountService.findByNumber(loanApplicationDTO.getToAccountNumber()) == null){
            return new ResponseEntity<>("la cuenta de destino no exite", HttpStatus.FORBIDDEN);
        }
    //Comprobar que la cuenta es del cliente logueado
        Set<Account> accounts = current.getAccounts();
        if(accounts.stream().filter(account -> account.getNumber().equals(loanApplicationDTO.getToAccountNumber())).collect(toList()).isEmpty()){
            return new ResponseEntity<>("La cuenta de Destino no pertence al client", HttpStatus.FORBIDDEN );
        }
    //Comprobar que el monto solicitado no exceda al maximo permitido
        if(loanApply.getMaxAmount() < loanApplicationDTO.getAmount()){
            return new ResponseEntity<>("El monto solicitado excede al monto maximo autorizado", HttpStatus.FORBIDDEN );
        }
    //Comprobar que la cantidad de cuotas solicitadas pertenezcan al prestamo
        if(!payments.contains(loanApplicationDTO.getPayments())){
            }

    // Crear el prestamo solicitado
        Double totalLoan = loanApplicationDTO.getAmount() * 120/100;
        ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getPayments(),totalLoan);
        clientLoan.setClient(current);
        clientLoan.setLoan(loanApply);
        clientLoanService.newClientLoan(clientLoan);

    // Crear transaccion y actualiza el saldo de la cuenta
        Transaction transaction = new Transaction(CREDIT, loanApplicationDTO.getAmount(),"description"+" "+ "loan aproved", LocalDate.now());
        transactionService.newTransaction(transaction);
        creditAccount.addTransaction(transaction);

        Double balanceToAccount = creditAccount.getBalance();
        creditAccount.setBalance(balanceToAccount+loanApplicationDTO.getAmount());

        return new ResponseEntity<>("201 created", HttpStatus.CREATED );
    }

}
