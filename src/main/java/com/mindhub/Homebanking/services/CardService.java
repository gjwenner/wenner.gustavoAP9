package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.models.Card;


import java.util.List;

public interface CardService {

    List<CardDTO> getCardDTO();

    Card findById(Long id);

    CardDTO getCardDTO(Long id);

    void newCard(Card card);
}
