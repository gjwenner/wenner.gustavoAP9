package com.mindhub.Homebanking.controllers;

import com.mindhub.Homebanking.dtos.TransactionDTO;
import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.models.Transaction;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.repositories.ClientRepository;
import com.mindhub.Homebanking.repositories.TransactionRepository;
import com.mindhub.Homebanking.services.AccountService;
import com.mindhub.Homebanking.services.ClientService;
import com.mindhub.Homebanking.services.TransactionService;
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
import static com.mindhub.Homebanking.models.TransactionType.DEBIT;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;


    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(){ return transactionService.getTransactionDTO();
    }

    @GetMapping("/transactions/{id}")
    public TransactionDTO getTransactionsById(@PathVariable Long id){
        return transactionService.getTransactionDTO(id);
    }


    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<?> createTransfer(@RequestParam Double amount, @RequestParam String fromAccountNumber,
                                            @RequestParam String toAccountNumber, @RequestParam String description,
                                            Authentication authentication) {
        // Obtener información del cliente autenticado
        Client current = clientService.findByEmail(authentication.getName());
        Account debitAccount = accountService.findByNumber(fromAccountNumber);
        Account creditAccount = accountService.findByNumber(toAccountNumber);

        // comprobar que los parmetros no lleguen del Front vacios/sin importe
        if(amount <= 0 || description.isEmpty()) {return new ResponseEntity<>
                ("El monto o la descripcion estan vacios", HttpStatus.FORBIDDEN);}
        // comprobar que los numeros de cuenta no lleguen del Front
        if(fromAccountNumber.isEmpty() || toAccountNumber.isEmpty()) {return new ResponseEntity<>
                ("El monto o la descripcion estan vacios", HttpStatus.FORBIDDEN);}
        if(fromAccountNumber.equals(toAccountNumber))// Comprobar que la cuenta de Debito y Credito no sean la misma
        {return new ResponseEntity<>( "las cuentas de origen y destino no pueden ser las mismas", HttpStatus.FORBIDDEN);
        }
        Set<Account> accounts = current.getAccounts();// Comprobar que la cuenta es del cliente logueado
        if(accounts.stream().filter(account -> account.getNumber().equals(fromAccountNumber)).collect(toList()).isEmpty())
        { return new ResponseEntity<>("La cuenta de Origen no pertence al client", HttpStatus.FORBIDDEN ); }
        // Comprobar que la cuenta de origen tenga Saldo Suficiente
        if(amount > debitAccount.getBalance()){
            return new ResponseEntity<>("El Saldo de sa cuenta es insuficiente para transferir", HttpStatus.FORBIDDEN);
        }


        // Crear la Transacion por debito
        Transaction transaction1 = new Transaction(DEBIT, amount * -1,"description", LocalDate.now());
        transactionService.newTransaction(transaction1);
        debitAccount.addTransaction(transaction1);

        Double balanceFromAccount = debitAccount.getBalance();
        debitAccount.setBalance(balanceFromAccount-amount);


        // Crear la transacion por credito
        Transaction transaction2 = new Transaction(CREDIT, amount,"description", LocalDate.now());
        transactionService.newTransaction(transaction2);
        creditAccount.addTransaction(transaction2);

        Double balanceToAccount = creditAccount.getBalance();
        creditAccount.setBalance(balanceToAccount+amount);

        return new ResponseEntity<>("201 created", HttpStatus.CREATED );
    }
}