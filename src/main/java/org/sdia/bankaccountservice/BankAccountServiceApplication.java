package org.sdia.bankaccountservice;

import org.sdia.bankaccountservice.entities.BankAccount;
import org.sdia.bankaccountservice.enums.AccountType;
import org.sdia.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);

    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository) {
        return args -> {
            System.out.println("************ Création de 10 comptes ************");

            for (int i = 1; i <= 10; i++) {
                BankAccount account = BankAccount.builder()
                        .id(UUID.randomUUID().toString()) // identifiant unique
                        .createdAt(new Date())
                        .balance(Math.random() * 10000) // solde aléatoire entre 0 et 10000
                        .currency(i % 2 == 0 ? "MAD" : "USD") // alterne entre MAD et USD
                        .type(i % 2 == 0 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVINGS_ACCOUNT)
                        .build();

                bankAccountRepository.save(account);
            }
        };
    }


}
