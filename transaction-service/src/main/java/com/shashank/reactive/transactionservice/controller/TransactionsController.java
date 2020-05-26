package com.shashank.reactive.transactionservice.controller;

import com.shashank.reactive.transactionservice.model.AccountHolder;
import com.shashank.reactive.transactionservice.model.Transaction;
import com.shashank.reactive.transactionservice.model.TransactionDetailsDTO;
import com.shashank.reactive.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDetailsDTO> doDeposit(@RequestBody Transaction transaction){
        return transactionService.deposit(transaction);
    }

    @PostMapping(value = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDetailsDTO> doWithdraw(@RequestBody Transaction transaction){
        return transactionService.withdraw(transaction);
    }

    @PostMapping(value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDetailsDTO> doTransfer(@RequestBody Transaction transaction){
        return transactionService.transfer(transaction);
    }

    @PostMapping(value = "/transactions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Transaction> getTransactions(@RequestBody AccountHolder accountHolder){
        return transactionService.getTransactions(accountHolder.getAccountNumber());
    }

}
