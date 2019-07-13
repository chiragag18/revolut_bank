package com.revolut.bank.repository;

import com.revolut.bank.model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Author by chirag.agrawal on date 6/30/2019
 */

public class AccountRepositoryImpl implements AccountRepository {

    private static AccountRepositoryImpl _SELF = new AccountRepositoryImpl();

    public static AccountRepositoryImpl getInstance(){
        return _SELF;
    }

    private Map<String, Account> accountMap = null;

    private AccountRepositoryImpl() {
        accountMap = new HashMap<>();
    }

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

    public Optional<Account> removeAccount(String accountNumber){
        if(accountMap.containsKey(accountNumber)) {
            return Optional.ofNullable(accountMap.remove(accountNumber));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> updateAccount(Account account) {
        if(accountMap.containsKey(account.getAccountNumber())) {
            return Optional.ofNullable(accountMap.put(account.getAccountNumber(), account));
        }
        return Optional.empty();
    }
}
