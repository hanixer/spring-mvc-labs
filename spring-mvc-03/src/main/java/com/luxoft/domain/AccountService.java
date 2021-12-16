package com.luxoft.domain;

import com.luxoft.domain.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account get(long id);

    long create(Account account);

    void deposit(long accountId, long amount);

    void withdraw(long accountId, long amount) throws NotEnoughFundsException;

    void changeHolder(long accountId, String newHolder);

    void delete(long accountId);
}
