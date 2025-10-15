package org.sdia.bankaccountservice.service;

import org.sdia.bankaccountservice.DTO.BankAccountRequestDTO;
import org.sdia.bankaccountservice.DTO.BankAccountResponseDTO;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);


    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
