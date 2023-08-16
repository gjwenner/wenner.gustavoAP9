package com.mindhub.Homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "clientId", fetch =FetchType.EAGER )
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "loan", fetch =FetchType.EAGER  )
    private Set<ClientLoan> clientLoans = new HashSet<>();

    //public List<Loan> getLoan() {
        //return Loans.stream().map(clientLoan -> clientLoan.getLoan()).collect(toList());
    //}


    public Client() {
    }

    public Client(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<ClientLoan> getLoans() { return clientLoans; }


    public void addAccount(Account account){
        account.setClientId(this);
        accounts.add(account);

    }

    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setClientId(this);
    //    loans.add(clientLoans);
    }
}
