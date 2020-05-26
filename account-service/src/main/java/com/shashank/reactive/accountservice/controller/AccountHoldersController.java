package com.shashank.reactive.accountservice.controller;

import com.shashank.reactive.accountservice.service.AccountService;
import com.shashank.reactive.accountservice.model.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/account-service")
public class AccountHoldersController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AccountHolder> getAccounts(){
       return accountService.getAllAccounts();
    }

    @GetMapping(value ="/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AccountHolder> getAccount(@RequestParam Long accountNumber) {
        return accountService.getaccountById(accountNumber);
    }

    @PostMapping(value ="/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AccountHolder> createAccount(@RequestBody AccountHolder account) {
        account.setAccountStartDate(LocalDateTime.now());
        return accountService.createAccount(account);
    }

    @PutMapping(value ="/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AccountHolder> updateAccount(@RequestBody AccountHolder account) {
        return accountService.updateAccount(account);
    }

    @DeleteMapping(value ="/account")
    public Mono<Void> deleteAccount(@RequestParam Long accountNumber) {
        return accountService.deleteAccount(accountNumber);
    }

    @GetMapping(value ="/account/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AccountHolder> getTransactionHistoryForAccount(@RequestParam Long accountNumber) {
        return accountService.getaccountById(accountNumber);
    }
}
