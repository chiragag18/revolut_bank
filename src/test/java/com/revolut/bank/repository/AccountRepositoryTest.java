package com.revolut.bank.repository;

import com.revolut.bank.model.Account;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;
import java.util.UUID;

/**
 * Author by chirag.agrawal on date 6/30/2019
 */

public class AccountRepositoryTest {

    private AccountRepository accountRepository = AccountRepository.getInstance();

    @Test
    public void createAccountWithThousandDollar(){
        String accountNumber = UUID.randomUUID().toString();
        Account account = new Account(accountNumber, new BigDecimal("1000.00"), Currency.getInstance("USD"));
        Optional<Account> savedAccount =  accountRepository.addAccount(account);
        Assert.assertEquals(true, savedAccount.isPresent());
    }

    @Test
    public void createAccountWithSameAccountNumber(){
        String accountNumber = UUID.randomUUID().toString();
        Account account = new Account(accountNumber, new BigDecimal("1000.00"), Currency.getInstance("USD"));
        Optional<Account> savedAccount =  accountRepository.addAccount(account);
        Assert.assertEquals(true, savedAccount.isPresent());
        savedAccount =  accountRepository.addAccount(account);
        Assert.assertEquals(Optional.empty(), savedAccount);
    }

    @Test
    public void validateAccountWithThousandDollar(){
        String accountNumber = UUID.randomUUID().toString();
        Account account = new Account(accountNumber, new BigDecimal("1000.00"), Currency.getInstance("USD"));
        accountRepository.addAccount(account);
        Optional<Account> savedAccount = accountRepository.findByAccountNumber(accountNumber);
        savedAccount.ifPresent(account1 -> {
            Assert.assertEquals(accountNumber, account1.getAccountNumber());
            Assert.assertEquals(new BigDecimal("1000.00"), account1.getBalance());
            Assert.assertEquals(Currency.getInstance("USD"), account1.getCurrency());
        });
    }

    @Test
    public void accountNotFoundByAccountNumber(){
        String accountNumber = UUID.randomUUID().toString();
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        Assert.assertEquals(Optional.empty(), account);
    }

}
