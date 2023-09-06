package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.dtos.ClientDTO;
import com.mindhub.Homebanking.models.Client;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getClientsDTO();

    Client findById(Long id);

    ClientDTO getClientDTO(Long id);

    Client findByEmail(String email);

    void newClient(Client client);

    Client getCurrent(String email);

}
