package com.revolut.bank.validator;

import com.revolut.bank.model.Account;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Author by chirag.agrawal on date 7/13/2019
 */

public class AccountValidator {

    public static void validateAccount(Account account) {
        Validator.isTrue(Objects.isNull(account.getAccountNumber()), "Account number not specified");
        validateAmount(account.getBalance());
        Validator.isTrue(Objects.isNull(account.getCurrency()), "Currency can not be null");
    }

    public static void validateAmount(BigDecimal amount){
        Validator.isTrue(Objects.isNull(amount), "Amount can not be left blank");
        Validator.isTrue(amount.signum() <=0, "Amount can not be <=0" );
    }
}
