package com.revolut.bank.repository;

import com.revolut.bank.model.Account;

import java.util.Optional;

/**
 * Author by chirag.agrawal on date 7/13/2019
 */

public interface AccountRepository {

    Optional<Account> addAccount(Account account);

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> removeAccount(String accountNumber);

    Optional<Account> updateAccount(Account account);

}
