package com.revolut.bank.repository;

import com.revolut.bank.model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Author by chirag.agrawal on date 6/30/2019
 */

public class AccountRepository {

    private AccountRepository() {}

    private static AccountRepository _SELF = new AccountRepository();

    public static AccountRepository getInstance(){
        return _SELF;
    }

    private Map<String, Account> accountMap = new HashMap<>();

    public Optional<Account> addAccount(Account account) {
        if(!accountMap.containsKey(account.getAccountNumber())) {
            accountMap.put(account.getAccountNumber(), account);
            return Optional.ofNullable(accountMap.get(account.getAccountNumber()));
        }
        return Optional.empty();
    }

    public Optional<Account> findByAccountNumber(String accountNumber) {
        if(accountMap.containsKey(accountNumber)) {
            return Optional.ofNullable(accountMap.get(accountNumber));
        }
        return Optional.empty();
    }
}
