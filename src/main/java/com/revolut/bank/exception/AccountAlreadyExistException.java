package com.revolut.bank.exception;

/**
 * Author by chirag.agrawal on date 6/30/2019
 */

public class AccountAlreadyExistException extends BusinessException {

    public AccountAlreadyExistException(String accountNumber) {
        super("Account already exist with accountNumber="+accountNumber);
    }
}
