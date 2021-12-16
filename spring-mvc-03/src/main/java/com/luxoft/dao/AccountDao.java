package com.luxoft.dao;

import com.luxoft.domain.model.Account;

import java.util.List;

public interface AccountDao {

    List<Account> getAll();

    Account get(long id);

    long insert(Account account);

    void update(Account account);

    void delete(long id);
}
