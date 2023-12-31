package com.mindhub.Homebanking;

import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository) {
		return (args) ->{
			Client client = new Client("Gustavo","Wenner","gjwenner@gmail.com");

			clientRepository.save(client);
		};
	}

}
