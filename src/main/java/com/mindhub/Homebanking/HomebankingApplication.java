package com.mindhub.Homebanking;

import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository) {
		return (args) ->{
			Client client1 = new Client("Gustavo","Wenner","gjwenner@gmail.com");
			Client client2 = new Client("Melba","Morel","melba@gmail.com");


			clientRepository.save(client1);
			clientRepository.save(client2);

			LocalDate today =  LocalDate.now();
			LocalDate tomorrow = today.plusDays(1);

			Account newAccount1 = new Account("VIN001", today, 5000.00);
			Account newAccount2 = new Account("VIN002", tomorrow, 7500.00);

			client1.addAccount(newAccount1);
			client1.addAccount(newAccount2);

			accountRepository.save(newAccount1);
			accountRepository.save(newAccount2);
		};


	}

}
