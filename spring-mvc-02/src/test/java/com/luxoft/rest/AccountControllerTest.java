package com.luxoft.rest;

import com.luxoft.domain.AccountService;
import com.luxoft.domain.model.Account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService service;

    @Test
    public void simple404Test() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void depositTest() throws Exception {
        given(service.get(1)).willReturn(new Account(1, "Mike", 1000));

        mvc.perform(put("/account/1/deposit").param("amount", "100")).andExpect(status().isOk());
    }

    // TODO*: add tests here
}