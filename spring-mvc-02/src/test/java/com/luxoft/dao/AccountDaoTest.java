package com.luxoft.dao;

import com.luxoft.domain.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDaoTest {
    @Autowired
    private AccountDao dao;

    @Test
    public void testGet() throws Exception {
        Account account = dao.get(1L);
        assertNotNull(account);
        assertEquals(1L, account.getId());
        assertEquals("Mike", account.getHolder());
        assertEquals(1300L, account.getBalance());
    }

    @Test
    public void testGetNotExisted() throws Exception {
        assertNull(dao.get(-1L));
    }

    @Test
    public void testInsert() throws Exception {
        Account account = new Account("Jane Doe", 100);
        long newId = dao.insert(account).getId();
        Account savedAccount = dao.get(newId);
        assertNotNull(savedAccount);
        assertEquals(newId, savedAccount.getId());
        assertEquals("Jane Doe", savedAccount.getHolder());
        assertEquals(100, savedAccount.getBalance());
    }

    @Test
    public void testUpdate() throws Exception {
        Account account = dao.get(1L);
        account.deposit(100L);
        account.setHolder("Mike Smith");
        dao.update(account);
        Account updatedAccount = dao.get(1L);
        assertEquals(account.getId(), updatedAccount.getId());
        assertEquals(account.getHolder(), updatedAccount.getHolder());
        assertEquals(account.getBalance(), updatedAccount.getBalance());
    }

    @Test
    public void testDelete() throws Exception {
        long newId = dao.insert(new Account("Temp", 200L)).getId();
        dao.delete(newId);
        Account deleted = dao.get(newId);
        assertNull(deleted);
    }
}