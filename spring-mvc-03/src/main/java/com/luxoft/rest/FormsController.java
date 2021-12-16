package com.luxoft.rest;

import com.luxoft.domain.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SuppressWarnings("unused")
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

        // TODO: add code here

        return "list"; // list.html
    }

    // TODO: add handler to show edit page

    // TODO: add handler to save changes in account

    // TODO: add deposit handler

    // TODO: add withdraw handler
}
