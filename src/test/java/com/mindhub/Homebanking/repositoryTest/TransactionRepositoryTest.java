package com.mindhub.Homebanking.repositoryTest;

import org.junit.jupiter.api.Test;
import com.mindhub.Homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;



@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Test

    public void xxxx(){

    }


}
