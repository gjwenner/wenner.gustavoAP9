package com.mindhub.Homebanking.controllers;


import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.models.Card;
import com.mindhub.Homebanking.models.CardColor;
import com.mindhub.Homebanking.models.CardType;
import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.repositories.CardRepository;
import com.mindhub.Homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/cards")
    public List<CardDTO> getCards(){
        List<Card>  allCards = cardRepository.findAll();
        List<CardDTO> convertedList;
        convertedList = allCards
                .stream()
                .map(currentcard -> new CardDTO(currentcard))
                .collect(toList());
        return convertedList;
    }

    @GetMapping("/cards/{id}")
    public CardDTO getCardsById(@PathVariable Long id){return new CardDTO(cardRepository.findById(id).orElse(null));
    }

    //@GetMapping("/clients/current/cards")
    //public List<CardDTO> getCards(Authentication authentication) {
    //    Client client = this.clientRepository.findByEmail(authentication.getName());
    //    return client.getCards().stream().map(CardDTO::new).collect(toList());
    //}

    @GetMapping("/clients/current/cards")
    public ResponseEntity<?> getCards(Authentication authentication) {
        // Buscar el cliente en la base de datos
        Client current = clientRepository.findByEmail(authentication.getName());
        if (current == null) {
            // Si no se encuentra el cliente, retornar un error 404 (Not Found)
            return ResponseEntity.notFound().build();
        }

        Set<Card> cards = current.getCards();
        if (cards == null || cards.isEmpty()) {
            // Si el cliente no tiene tarjetas, retornar una respuesta vacía
            return ResponseEntity.noContent().build();
        }

        // Convertir las tarjetas a DTOs y retornarlas en una lista
        List<CardDTO> cardDTOs = cards.stream().map(CardDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(cardDTOs);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    //Para generar el Numero de Tarjeta
    public String cardNumberG(){
        String cardNumber = "";
        for(int i=0;i<4;i++) {
            int num = (getRandomNumber(1000, 9999));
            if (i != 3) {
                cardNumber += num + "-";
            } else {
                cardNumber += num;
            }
        }
        return cardNumber;
    }

    //Para generar CVV
    public int CVV(){
        int numcvv = (getRandomNumber(1, 999));
        return numcvv;
    }


    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(@RequestParam CardType cardType, @RequestParam CardColor cardColor,
                                              Authentication authentication) {
        Client current = clientRepository.findByEmail(authentication.getName());
        Set<Card> cards = current.getCards();//Cards del cliente logueado
        if (!cards.stream().filter(card -> card.getType().equals(cardType)).//Filtro la tarjeta del cliente por typo
                            filter(card -> card.getColor().equals(cardColor)).//Filtro las card que me quedaron por color
                            collect(toList()).isEmpty()){
            return new ResponseEntity<>("You have a credit Card "+cardType+" "+cardColor, HttpStatus.FORBIDDEN );
        }

    //Crear el Objeto Card
        Card card = new Card(current.getFirstName()+" "+current.getLastName(),
                cardType, cardColor, cardNumberG(), CVV() , LocalDate.now(), LocalDateTime.now().plusYears(5));

    //Asignar la Tarjeta al cliente
        current.addCard(card);

    //Guardar la tarjeta en Base de Datos
        cardRepository.save(card);

    //Retornar Respuesta

        //cardRepository.save(new Card(client.getFirstName()+" "+client.getLastName(),
        //        cardType, cardColor, cardNumberG(), CVV() , LocalDate.now(), LocalDateTime.now().plusYears(5)));
        return new ResponseEntity<>("201 created",HttpStatus.CREATED);

    }
}