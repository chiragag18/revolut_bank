package com.revolut.bank.service;

import com.revolut.bank.exception.AccountAlreadyExistException;
import com.revolut.bank.exception.AccountNotFoundException;
import com.revolut.bank.model.Account;
import com.revolut.bank.repository.AccountRepositoryImpl;
import com.revolut.bank.validator.AccountValidator;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

/**
 * Author by chirag.agrawal on date 6/30/2019
 */

public class AccountServiceImpl implements AccountService {

    private static AccountServiceImpl _SELF = new AccountServiceImpl();

    public static AccountServiceImpl getInstance() {
        return _SELF;
    }

    private AccountRepositoryImpl accountRepository = AccountRepositoryImpl.getInstance();

    private AccountServiceImpl(){}

    @Override
    public Account createAccount(BigDecimal amount, Currency currency) {
        AccountValidator.validateAmount(amount);
        String accountId = UUID.randomUUID().toString();
        Account newAccount = new Account(accountId, amount, currency);
        return accountRepository.addAccount(newAccount).
                orElseThrow(() ->new AccountAlreadyExistException(accountId));
    }

    @Override
    public Account updateAccountBalance(String accountNumber, BigDecimal amount) {
        Account account = getAccountDetails(accountNumber);
        AccountValidator.validateAmount(amount);
        Account newAccountInfo = new Account(account.getAccountNumber(), amount, account.getCurrency());
        accountRepository.updateAccount(newAccountInfo);
        return getAccountDetails(accountNumber);
    }

    @Override
    public Account getAccountDetails(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).
                orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    @Override
    public Account removeAccount(String accountNumber) {
        return accountRepository.removeAccount(accountNumber).
                orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }
}
