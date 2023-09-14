package com.mindhub.Homebanking;

import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.Homebanking.models.TransactionType.CREDIT;
import static com.mindhub.Homebanking.models.TransactionType.DEBIT;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
    //@Autowired
    //PasswordEncoder passwordEncoder;

    @Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
		return (args) ->{
			/*Client client1 = new Client("Melba","Morel","melba@gmail.com", passwordEncoder.encode("123"));
			Client client2 = new Client("Gustavo","Wenner","gjwenner@gmail.com", passwordEncoder.encode("123"));

			clientRepository.save(client1);
			clientRepository.save(client2);

			//LocalDate today =  LocalDate.now();
			//LocalDate tomorrow = today.plusDays(1);

			Account newAccount1 = new Account("VIN0000001", LocalDate.now(), 5000.00);
			Account newAccount2 = new Account("VIN0000002", LocalDate.now().plusDays(1), 7500.00);
			Account newAccount3 = new Account("VIN0000003", LocalDate.now().plusDays(1), 10000.00);
			Account newAccount4 = new Account("VIN0000004", LocalDate.now().plusDays(1), 15500.00);

			client1.addAccount(newAccount1);
			client1.addAccount(newAccount2);
			client2.addAccount(newAccount3);
			client2.addAccount(newAccount4);
			accountRepository.save(newAccount1);
			accountRepository.save(newAccount2);
			accountRepository.save(newAccount3);
			accountRepository.save(newAccount4);

			Transaction newTransaction1 = new Transaction(CREDIT, 200.00, "Deposit", LocalDate.now());
			Transaction newTransaction2 = new Transaction(DEBIT, -100.00, "Payment", LocalDate.now().plusDays(1));

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

			ClientLoan clientLoan1 = new ClientLoan(60, 400000.00);
			clientLoan1.setClient(client1);
			clientLoan1.setLoan(newLoan1);
			clientLoanRepository.save(clientLoan1);

			ClientLoan clientLoan2 = new ClientLoan(12, 50000.00);
			clientLoan2.setClient(client1);
			clientLoan2.setLoan(newLoan2);
			clientLoanRepository.save(clientLoan2);

			ClientLoan clientLoan3 = new ClientLoan(24, 100000.00);
			clientLoan3.setClient(client2);
			clientLoan3.setLoan(newLoan3);
			clientLoanRepository.save(clientLoan3);

			//ClientLoan clientLoan4 = new ClientLoan(36, 200000.00, client2, newLoan3);
			//clientLoanRepository.save(clientLoan4);

			LocalDate from =  LocalDate.now();
			LocalDateTime thru = LocalDateTime.now().plusYears(5);

			Card card1 = new Card(client1.getFirstName()+" "+ client1.getLastName(), CardType.CREDIT, CardColor.GOLD,"5832-5248-2365", 541, from,thru);
			Card card2 = new Card(client1.getFirstName()+" "+ client1.getLastName(), CardType.CREDIT, CardColor.SILVER,"4823-4887-6954", 103, from,thru);
			Card card3 = new Card(client2.getFirstName()+" "+ client2.getLastName(), CardType.DEBIT, CardColor.TITANIUM,"6847-2341-5814", 974, from,thru);

			card1.setClient(client1);
			card2.setClient(client1);
			card3.setClient(client2);

			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);

			client1.addCard(card1);
			client1.addCard(card2);
			client2.addCard(card3);
		*/};

	}

}



