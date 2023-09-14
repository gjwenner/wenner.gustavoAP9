package com.mindhub.Homebanking.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class CardUtilTest {

    @Test
    public void cardNumberG() {
        String cardNumber = CardUtil.cardNumberG();
        assertThat(cardNumber,is(not(emptyOrNullString())));
    }

    @Test
    public void CVV() {
        int numcvv =  CardUtil.getRandomNumber(1, 999);
       assertThat(numcvv, allOf(greaterThan(0),lessThan(10000), not(equalTo(0))));
    }
}