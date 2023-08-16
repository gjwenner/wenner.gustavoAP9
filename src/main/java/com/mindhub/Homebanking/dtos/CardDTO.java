package com.mindhub.Homebanking.dtos;

public class CardDTO {
    private Long Id;
    private String cardholder;
    private CardType type;
    private CardColor color;
    private String  cardNumber;
    private Short cvv;
    private Date fromDate;
    private Date thruDate;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardholder = card.get cardholder ();
        this.type = card.getType();
        this.color = card.getColor();
        this.cardNumber  = card.getcardNumber ();
        this.cvv  = card.getcvv();
        this.fromDate  = card.getfromDate();
        this.thruDate = card.getthruDate();

    }
