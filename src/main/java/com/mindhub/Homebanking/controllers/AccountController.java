package com.mindhub.Homebanking.controllers;

import com.mindhub.Homebanking.dtos.AccountDTO;
import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.dtos.ClientDTO;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.repositories.ClientRepository;
import com.mindhub.Homebanking.services.AccountService;
import com.mindhub.Homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.mindhub.Homebanking.models.TransactionType.CREDIT;
import static com.mindhub.Homebanking.models.TransactionType.DEBIT;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        return accountService.getAccountsDTO(); }
    @GetMapping("/accounts/{id}")
    public AccountDTO getAccountsById(@PathVariable Long id){
        return accountService.getAccountDTO(id) ;
    }

    @GetMapping("/clients/current/accounts")
    public List<AccountDTO> getAccount(Authentication authentication) {
        Client client = this.clientService.getCurrent(authentication.getName());
        return client.getAccounts().stream().map(AccountDTO::new).collect(toList());
    }
    //Para crear un numero aleatorio
    //public int getRandomNumber(int min, int max) {
        //return (int) ((Math.random() * (max - min)) + min);
    //}

    //Para comprobar que el numero de cuenta no se repita
    //public String createNumber(){
    //    String numbers = "VIN-" + getRandomNumber(1,99999999);
    //    return numbers;
    //}

    //public String createNumberAccount(){
    //    String number;
    //    do{
    //        number = createNumber();
    //    }while (accountRepository.existByNumber(number));
    //    return createNumberAccount();
    //}

    //Para crear una nueva cuenta
    //@PostMapping("/clients/current/accounts")
    //public ResponseEntity<Object> createAccount(Authentication authentication){
      //  Client current = clientRepository.findByEmail(authentication.getName());
        //Set<Account> account = client.getAccounts();// Busco las cuentas de cliente logueado
    //    if (current.getAccounts().size() >= 3){
    //        return new ResponseEntity<>("403 You have 3 accounts",HttpStatus.FORBIDDEN);
    //    }
    //    current.addAccount(accountRepository.save(new Account("VIN" + getRandomNumber(1,99999999),
    //        LocalDate.now(),0.00)));
    //        return new ResponseEntity<>("201 created",HttpStatus.CREATED);
    //}
    @PostMapping("/clients/current/accounts")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<?> createAccount(Authentication authentication) {

        // Obtener información del cliente autenticado
        Client current = clientService.getCurrent(authentication.getName());

        // Verificar si el cliente ya tiene 3 cuentas registradas
        if (current.getAccounts().size() >= 3) {
            return new ResponseEntity<>("403 prohibido", HttpStatus.FORBIDDEN);
        }

        // Generar número aleatorio de 4 dígitos y crear número de cuenta
        Random rand = new Random();
        int random = rand.nextInt(99999999) + 1;
        String number = "VIN-" + random;

        // Verificar si el número de cuenta ya existe en la base de datos
        while (accountService.findByNumber(number) != null && !accountService.findByNumber(number).getNumber().equals(number)) {
            random = rand.nextInt(99999999) + 1;
            number = "VIN-" + random;
        }

        // Crear objeto Account
        Account account = new Account(number, LocalDateTime.now(), 0.00);

        // Asignar cuenta al cliente
        current.addAccount(account);

        // Guardar cuenta en la base de datos
        accountService.newAccount(account);

        // Retornar respuesta "201 creada"
        return new ResponseEntity<>("201 creada", HttpStatus.CREATED);
        }
    }

