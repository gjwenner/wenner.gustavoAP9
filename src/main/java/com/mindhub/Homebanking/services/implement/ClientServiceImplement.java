package com.mindhub.Homebanking.services.implement;

import com.mindhub.Homebanking.dtos.ClientDTO;
import com.mindhub.Homebanking.repositories.ClientRepository;
import com.mindhub.Homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

   @Override
    public List<ClientDTO> getClientDTO() {
        return null;
    }
}
