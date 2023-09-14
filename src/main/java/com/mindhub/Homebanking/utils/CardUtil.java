package com.mindhub.Homebanking.utils;

public class CardUtil {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String cardNumberG(){
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
    public static int CVV(){
        int numcvv = (getRandomNumber(1, 999));
        return numcvv;
    }
}
