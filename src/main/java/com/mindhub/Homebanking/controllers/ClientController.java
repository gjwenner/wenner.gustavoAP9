package com.mindhub.Homebanking.controllers;

import com.mindhub.Homebanking.dtos.ClientDTO;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.repositories.ClientRepository;
import com.mindhub.Homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.mindhub.Homebanking.models.Client;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountController accountController;
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
     public List<ClientDTO> getClients(){
        return clientService.getClientsDTO();
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClientsById(@PathVariable Long id){ return clientService.getClientDTO(id);
    }
    
    
    //Get /api/client/current JSon con los datos del cliente autenticado
    @GetMapping("/clients/current")
    public ClientDTO getCurrent(Authentication authentication){
        return new ClientDTO( clientService.getCurrent(authentication.getName()));
    }

    //Post para crear el cliente
    //@PostMapping("/clients")
    // Client client = clientRepository.findByEmail(); //Para verificar que el mail no este registrado
    @RequestMapping(path = "/clients", method = RequestMethod.POST)

    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientService.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }

        //clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
        Client newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientService.newClient(newClient);
        accountController.createAccount(new UsernamePasswordAuthenticationToken(newClient.getEmail(), newClient.getPassword ()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
