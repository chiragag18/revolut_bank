package com.revolut.bank.exception;

/**
 * Author by chirag.agrawal on date 06/30/2019
 */

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
