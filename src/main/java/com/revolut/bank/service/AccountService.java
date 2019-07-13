package com.revolut.bank.service;

import com.revolut.bank.model.Account;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Author by chirag.agrawal on date 7/13/2019
 */

public interface AccountService {

    Account createAccount(BigDecimal amount, Currency currency);

    Account updateAccountBalance(String accountNumber, BigDecimal amount);

    Account getAccountDetails(String accountNumber);

    Account removeAccount(String accountNumber);
}

