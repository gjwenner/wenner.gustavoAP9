package com.mindhub.Homebanking.services.implement;

import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.dtos.ClientDTO;
import com.mindhub.Homebanking.models.Card;
import com.mindhub.Homebanking.repositories.CardRepository;
import com.mindhub.Homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImplement implements CardService {

    @Autowired
    CardRepository cardRepository;


    @Override
    public List<CardDTO> getCardDTO() {
        return cardRepository.findAll().stream().map(CardDTO::new).collect(Collectors.toList());
    }
    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public CardDTO getCardDTO(Long id) {
        return new CardDTO(this.findById(id));
    }

    @Override
    public void newCard(Card card) {
        cardRepository.save(card);
    }
}
