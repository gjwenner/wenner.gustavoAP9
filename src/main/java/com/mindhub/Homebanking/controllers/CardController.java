package com.mindhub.Homebanking.controllers;


import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.dtos.ClientDTO;
import com.mindhub.Homebanking.models.Card;
import com.mindhub.Homebanking.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/cards")
    public List<CardDTO> getCards(){
        List<Card>  allCards = cardRepository.findAll();
        List<CardDTO> convertedList;
        convertedList = allCards
                .stream()
                .map(currentcard -> new CardDTO(currentcard))
                .collect(Collectors.toList());
        return convertedList;
    }

    @GetMapping("/cards/{id}")
    public CardDTO getCardsById(@PathVariable Long id){return new CardDTO(cardRepository.findById(id).orElse(null));


    }

}
