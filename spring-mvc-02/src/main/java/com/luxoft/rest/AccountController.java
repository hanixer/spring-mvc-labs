package com.luxoft.rest;

import com.luxoft.domain.AccountService;
import com.luxoft.domain.NotEnoughFundsException;
import com.luxoft.domain.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @GetMapping("/account/{id}")
    public AccountDTO get(@PathVariable("id") long id) {
        Account account = service.get(id);
        return AccountDTO.toDto(account);
    }

    @PostMapping("/account/")
    public AccountDTO create(AccountDTO dto) {
        Account account = AccountDTO.toDomainObject(dto);
        Account accountWithID = service.create(account);
        return AccountDTO.toDto(accountWithID);
    }

    @PutMapping("/account/{id}/deposit")
    public void deposit(@PathVariable("id") long id, long amount) {
        service.deposit(id, amount);
    }

    @PutMapping("/account/{id}/withdraw")
    public void withdraw(@PathVariable("id") long id, long amount) throws NotEnoughFundsException {
        service.withdraw(id, amount);
    }

    @PutMapping("/account/{id}/holder")
    public void holder(@PathVariable("id") long id, String name) throws NotEnoughFundsException {
        service.changeHolder(id, name);
    }

    @ExceptionHandler(NotEnoughFundsException.class)
    public ResponseEntity<String> handleNotEnoughFunds(
            NotEnoughFundsException ex) {
        return ResponseEntity.badRequest().body(
                "Not enough funds (" + ex.getBalance()
                        + ") on account " + ex.getId()
                        + " to withdraw " + ex.getAmount());
    }
}
