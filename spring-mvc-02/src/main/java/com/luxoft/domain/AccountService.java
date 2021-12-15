package com.luxoft.domain;

import com.luxoft.domain.model.Account;

public interface AccountService {

    Account get(long id);

    Account create(Account account);

    void deposit(long accountId, long amount);

    void withdraw(long accountId, long amount) throws NotEnoughFundsException;

    void changeHolder(long accountId, String newHolder);

    void delete (long accountId);
}
