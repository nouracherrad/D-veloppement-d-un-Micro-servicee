package org.sdia.bankaccountservice.service;

import jakarta.transaction.Transactional;
import org.sdia.bankaccountservice.DTO.BankAccountRequestDTO;
import org.sdia.bankaccountservice.DTO.BankAccountResponseDTO;
import org.sdia.bankaccountservice.Mappers.AccountMapper;
import org.sdia.bankaccountservice.entities.BankAccount;
import org.sdia.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount =BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .currency(bankAccountDTO.getCurrency())
                .type(bankAccountDTO.getType())
                .build();
BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDTO;
    }
}
