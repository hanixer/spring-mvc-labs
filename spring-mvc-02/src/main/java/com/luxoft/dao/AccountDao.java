package com.luxoft.dao;

import com.luxoft.domain.model.Account;

public interface AccountDao {

    Account get(long id);

    Account insert(Account account);

    void update(Account account);

    void delete(long id);
}
