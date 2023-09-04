package com.mindhub.Homebanking.controllers;

import com.mindhub.Homebanking.dtos.ClientLoanDTO;
import com.mindhub.Homebanking.dtos.LoanApplicationDTO;
import com.mindhub.Homebanking.dtos.LoanDTO;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mindhub.Homebanking.models.TransactionType.CREDIT;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class LoansController {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientLoanRepository clientLoanRepository;


    @GetMapping("/loans")
    public List<LoanDTO> getLoan(){
        List<Loan> allLoans = loanRepository.findAll();

        List<LoanDTO> convertedList;
        convertedList = allLoans
                .stream()
                .map(currentloans -> new LoanDTO(currentloans))
                .collect(Collectors.toList());

        return convertedList;
    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> createLoans(@RequestBody LoanApplicationDTO loanApplicationDTO,
                                              Authentication authentication){
    //Obtener información del cliente autenticado
       Client current = clientRepository.findByEmail(authentication.getName());
       Account creditAccount = accountRepository.findByNumber(loanApplicationDTO.getToAccountNumber());
       //Loan loan = loanRepository.findById(Long);

    //Comprobar que los parametros no sean nulos
       //if(loanApplicationDTO.getLoanTypeId().isEmpty()
        //|| loanApplicationDTO.getAmount().isEmpty()
        //|| loanApplicationDTO.getPayments().isEmpty()|| loanApplicationDTO.getToAccountNumber().isBlank()){
       // return new ResponseEntity<>("los datos no pueden estra vacios", HttpStatus.FORBIDDEN);
      // }
    // Comprobar que la cuenta de destino existe

    //Comprobar que la cuenta es del cliente logueado
        Set<Account> accounts = current.getAccounts();
        if(accounts.stream().filter(account -> account.getNumber().equals(loanApplicationDTO.getToAccountNumber())).collect(toList()).isEmpty())
        { return new ResponseEntity<>("La cuenta de Destino no pertence al client", HttpStatus.FORBIDDEN ); }
    //Comprobar que el credito exista
    //    .contains(loanApplicationDTO.getLoanTypeId()) con los id de los prestamos creados

        //{ return new ResponseEntity<>("El prestamo solicitado no existe", HttpStatus.FORBIDDEN ); }
    //Comprobar que el monto solicitado no exceda al maximo permitido
    //    loanApplicationDTO.getAmount() con el monto del
    //Comprobar que la cantidd de cuotas solicitadas pertenezcan al prestamo

    // Crear el prestamo solicitado
    ClientLoan clientLoan = new ClientLoan();
    clientLoanRepository.save(clientLoan);


    // Crear transaccion y actualiza el saldo de la cuenta
    Transaction transaction = new Transaction(CREDIT, loanApplicationDTO.getAmount(),"description"+" "+ "loan aproved", LocalDate.now());
    transactionRepository.save(transaction);
    creditAccount.addTransaction(transaction);

    Double balanceToAccount = creditAccount.getBalance();
    creditAccount.setBalance(balanceToAccount+loanApplicationDTO.getAmount());

    return new ResponseEntity<>("201 created", HttpStatus.CREATED );
    }

}
