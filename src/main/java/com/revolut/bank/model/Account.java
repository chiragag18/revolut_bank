package com.revolut.bank.model;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Author by chirag.agrawal on date 6/30/2019
 */

public class Account {

    private String accountNumber;

    private BigDecimal balance;

    private Currency currency;

    public Account(String accountNumber, BigDecimal balance, Currency currency) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

}
