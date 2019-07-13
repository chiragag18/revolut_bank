package com.revolut.bank.validator;

/**
 * Author by chirag.agrawal on date 7/13/2019
 */

public class Validator {

    static void isTrue(boolean expression, String message) {
        if(expression)
            throw new IllegalArgumentException(message);
    }
}
