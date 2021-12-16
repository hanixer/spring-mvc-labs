package com.luxoft.rest;

import com.luxoft.domain.AccountService;
import com.luxoft.domain.NotEnoughFundsException;
import com.luxoft.domain.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormsController {

    private final AccountService accountService;

    @Autowired
    public FormsController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * this method shows list of all accounts
     */
    @GetMapping("/")
    public String listPage(Model model) {

        model.addAttribute("accounts", accountService.getAll());

        return "list"; // list.html
    }

    @GetMapping("/edit")
    public String edit(long id, Model model) {
        Account account = accountService.get(id);
        model.addAttribute("account", account);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(long id, String holder) {
        accountService.changeHolder(id, holder);
        return "redirect:edit?id=" + id;
    }

    @PostMapping("/deposit")
    public String deposit(long id, int amount) {
        accountService.deposit(id, amount);
        return "redirect:edit?id=" + id;
    }

    @PostMapping("/withdraw")
    public String withdraw(long id, int amount) throws NotEnoughFundsException {
        accountService.withdraw(id, amount);
        return "redirect:edit?id=" + id;
    }

    @PostMapping("/delete")
    public String delete(long id) {
        accountService.delete(id);
        return "redirect:/";
    }

    @ExceptionHandler(NotEnoughFundsException.class)
    public ResponseEntity<String> handleNotEnoughFunds(NotEnoughFundsException ex) {
        return ResponseEntity.badRequest().body(
                "Not enough funds (" + ex.getBalance()
                        + ") on account " + ex.getId()
                        + " to withdraw " + ex.getAmount());
    }

}
