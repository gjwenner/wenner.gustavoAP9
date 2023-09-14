package com.mindhub.Homebanking.repositoryTest;


import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void existClients(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients,not(empty()));
    }

    @Test
    public void emailNotNull(){
        List<String> clients = clientRepository.findAll().stream().map(client -> client.getEmail()).collect(Collectors.toList());
        assertThat(clients,not(empty()));
    }

    @Test
    public void passwordNotNull() {
        List<String> clients = clientRepository.findAll().stream().map(client -> client.getPassword()).collect(Collectors.toList());
        assertThat(clients,not(empty()));

    }
}