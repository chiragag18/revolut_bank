package com.revolut.bank.repository;

import com.revolut.bank.model.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;
import java.util.UUID;

/**
 * Author by chirag.agrawal on date 6/30/2019
 */

public class AccountRepositoryTest {

    private AccountRepository accountRepository = AccountRepositoryImpl.getInstance();

    private Account account;

    @Before
    public void init() {
        String accountNumber = UUID.randomUUID().toString();
        account = new Account(accountNumber, new BigDecimal("1000.00"), Currency.getInstance("USD"));
    }

    @After
    public void cleanUp(){
        accountRepository.removeAccount(account.getAccountNumber());
    }

    @Test
    public void shouldCreateAnAccountWithThousandDollar(){
        Optional<Account> savedAccount =  accountRepository.addAccount(account);
        Assert.assertTrue(savedAccount.isPresent());
    }

    @Test
    public void shouldNotDuplicateAnAccountWithSameAccountNumber(){
        Optional<Account> savedAccount =  accountRepository.addAccount(account);
        Assert.assertEquals(true, savedAccount.isPresent());
        savedAccount =  accountRepository.addAccount(account);
        Assert.assertEquals(Optional.empty(), savedAccount);
    }

    @Test
    public void shouldCreateAndValidateAnAccountWithThousandDollar(){
        accountRepository.addAccount(account);
        Optional<Account> savedAccount = accountRepository.findByAccountNumber(account.getAccountNumber());
        savedAccount.ifPresent(account1 -> {
            Assert.assertEquals(account.getAccountNumber(), account1.getAccountNumber());
            Assert.assertEquals(account.getBalance(), account1.getBalance());
            Assert.assertEquals(account.getCurrency(), account1.getCurrency());
        });
    }

    @Test
    public void shouldReturnEmptyAccountWithAccountNumber(){
        String accountNumber = UUID.randomUUID().toString();
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        Assert.assertEquals(Optional.empty(), account);
    }

    @Test
    public void updateAccountWithInvalidAccountNumberShouldReturnEmpty() {
        Optional<Account> savedAccount = accountRepository.updateAccount(account);
        Assert.assertFalse(savedAccount.isPresent());
    }

}
