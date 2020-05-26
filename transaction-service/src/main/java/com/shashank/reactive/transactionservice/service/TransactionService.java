package com.shashank.reactive.transactionservice.service;

import com.shashank.reactive.transactionservice.dao.AccountRepository;
import com.shashank.reactive.transactionservice.dao.TransactionRepository;
import com.shashank.reactive.transactionservice.model.TransactionDetailsDTO;
import com.shashank.reactive.transactionservice.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    public Mono<TransactionDetailsDTO> deposit(Transaction transaction) {
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setTransactionDate(LocalDateTime.now());
        return transactionRepository.save(transaction).then(accountRepository.findById(transaction.getAccountNumber()).map(accountHolder -> {
            BigDecimal updatedBalance = accountHolder.getAccountBalance().add(transaction.getDepositAmount());
            accountHolder.setAccountBalance(updatedBalance);
            return accountHolder;
        })).flatMap(accountHolder -> accountRepository.save(accountHolder).map(accountHolder1 -> TransactionDetailsDTO.builder().status("SUCCESS")
                .transactionAmount(transaction.getDepositAmount())
                .transactionId(transaction.getTransactionId())
                .transactionDate(transaction.getTransactionDate())
                .transactionType("DEPOSIT")
                .build()));

    }


    @Transactional
    public Mono<TransactionDetailsDTO> transfer(Transaction transaction) {
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setTransactionDate(LocalDateTime.now());
        return transactionRepository.save(transaction).then(accountRepository.findById(transaction.getAccountNumber()).map(accountHolder -> {
            BigDecimal updatedBalance = accountHolder.getAccountBalance().add(transaction.getDepositAmount());
            accountHolder.setAccountBalance(updatedBalance);
            return accountHolder;
        })).flatMap(accountHolder -> accountRepository.save(accountHolder)
                .then(accountRepository.findById(transaction.getSourceAccountNumber()).map(sourceAccountHolder -> {
                    BigDecimal updatedBalance = sourceAccountHolder.getAccountBalance().subtract(transaction.getDepositAmount());
                    sourceAccountHolder.setAccountBalance(updatedBalance);
                    return sourceAccountHolder;
                })).flatMap(sourceAccountHolder -> accountRepository.save(sourceAccountHolder).map(accountHolder1 -> TransactionDetailsDTO.builder().status("SUCCESS")
                        .transactionAmount(transaction.getDepositAmount())
                        .transactionId(transaction.getTransactionId())
                        .transactionDate(transaction.getTransactionDate())
                        .transactionType("TRANSFER")
                        .build())));
    }

    @Transactional
    public Mono<TransactionDetailsDTO> withdraw(Transaction transaction) {
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setTransactionDate(LocalDateTime.now());
        return transactionRepository.save(transaction).then(accountRepository.findById(transaction.getAccountNumber()).map(accountHolder -> {
            BigDecimal updatedBalance = accountHolder.getAccountBalance().subtract(transaction.getWithdrawAmount());
            accountHolder.setAccountBalance(updatedBalance);
            return accountHolder;
        })).flatMap(accountHolder -> accountRepository.save(accountHolder).map(accountHolder1 -> TransactionDetailsDTO.builder().status("SUCCESS")
                .transactionAmount(transaction.getWithdrawAmount())
                .transactionId(transaction.getTransactionId())
                .transactionDate(transaction.getTransactionDate())
                .transactionType("WITHDRAW")
                .build()));

    }

    public Flux<Transaction> getTransactions(Long accountNumber){
        return transactionRepository.findAllById(Mono.just(accountNumber));
    }
}
