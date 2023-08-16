package com.mindhub.Homebanking;

import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

import static com.mindhub.Homebanking.models.TransactionType.CREDIT;
import static com.mindhub.Homebanking.models.TransactionType.DEBIT;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository) {
		return (args) ->{
			Client client1 = new Client("Melba","Morel","melba@gmail.com");
			Client client2 = new Client("Gustavo","Wenner","gjwenner@gmail.com");

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

			Transaction newTransaction1 = new Transaction(CREDIT, 200.00, "Deposit", today);
			Transaction newTransaction2 = new Transaction(DEBIT, 100.00, "Payment", tomorrow);

			newAccount1.addTransaction(newTransaction1);
			newAccount1.addTransaction(newTransaction2);

			transactionRepository.save(newTransaction1);
			transactionRepository.save(newTransaction2);

			Loan newLoan1 = new Loan ("Hipotecario", 500000.00, List.of(12,24,36,48,60));
			Loan newLoan2 = new Loan ("Personal", 100000.00, List.of(6,12,24));
			Loan newLoan3 = new Loan ("Automotriz", 300000.00, List.of(6,12,24,36));

			loanRepository.save(newLoan1);
			loanRepository.save(newLoan2);
			loanRepository.save(newLoan3);

		};
	}

}



