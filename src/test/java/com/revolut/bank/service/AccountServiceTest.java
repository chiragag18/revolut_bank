package com.revolut.bank.service;

import com.revolut.bank.exception.AccountNotFoundException;
import com.revolut.bank.model.Account;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

/**
 * Author by chirag.agrawal on date 6/30/2019
 */

public class AccountServiceTest {

    private AccountService accountService = AccountServiceImpl.getInstance();

    private Account account;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {}

    @After
    public void cleanUp(){
        if(null != account)
            accountService.removeAccount(account.getAccountNumber());
    }

    @Test
    public void shouldCreateAccountWithHundredDollarInUSD(){
        account = accountService.createAccount(new BigDecimal("100.00"), Currency.getInstance("USD"));
        Assert.assertNotNull(account);
        Assert.assertNotNull(account.getAccountNumber());
        Assert.assertEquals(new BigDecimal("100.00"), account.getBalance());
        Assert.assertEquals(Currency.getInstance("USD"), account.getCurrency());
    }

    @Test
    public void shouldCreateAndValidateAccount(){
        account = accountService.createAccount(new BigDecimal("100.00"), Currency.getInstance("USD"));
        Assert.assertNotNull(account);
        Account savedAccount = accountService.getAccountDetails(account.getAccountNumber());
        Assert.assertNotNull(savedAccount);
        Assert.assertEquals(account.getBalance(), savedAccount.getBalance());
        Assert.assertEquals(savedAccount.getCurrency(), account.getCurrency());
    }

    @Test
    public void shouldThrowExceptionWhenAccountBalanceIsLessThanZero() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Amount can not be <=0");
        account = accountService.createAccount(new BigDecimal("-100.00"), Currency.getInstance("USD"));
    }

    @Test
    public void createAndUpdateBalanceAfterSuccessfulCreation(){
        account = accountService.createAccount(new BigDecimal("1000.00"), Currency.getInstance("USD"));
        Assert.assertNotNull(account);
        account = accountService.updateAccountBalance(account.getAccountNumber(), new BigDecimal("2000.00"));
        Assert.assertEquals(new BigDecimal("2000.00"), account.getBalance());
    }

    @Test
    public void removeAccountShouldThrowExceptionWhenAccountNumberNotExist() {
        String accountNumber = UUID.randomUUID().toString();
        thrown.expect(AccountNotFoundException.class);
        thrown.expectMessage("No account exist with number=" + accountNumber);
        account = accountService.removeAccount(accountNumber);
    }

}