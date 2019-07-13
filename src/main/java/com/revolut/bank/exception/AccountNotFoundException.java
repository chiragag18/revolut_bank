package com.revolut.bank.exception;

/**
 * Author by chirag.agrawal on date 7/13/2019
 */

public class AccountNotFoundException extends BusinessException {

    public AccountNotFoundException(String accountNumber) {
        super("No account exist with number=" + accountNumber);
    }
}
