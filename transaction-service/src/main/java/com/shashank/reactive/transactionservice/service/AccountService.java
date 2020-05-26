package com.shashank.reactive.transactionservice.service;


import com.shashank.reactive.transactionservice.dao.AccountRepository;
import com.shashank.reactive.transactionservice.model.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Flux<AccountHolder> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Mono<AccountHolder> getaccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Transactional
    public Mono<AccountHolder> createAccount(AccountHolder account) {
        return accountRepository.save(account);
    }

    @Transactional
    public Mono<AccountHolder> updateAccount(AccountHolder account) {
        return accountRepository.findById(account.getAccountNumber()).map(updateAccount -> {
            if(account.getAccountFirstName()!=null){
                updateAccount.setAccountFirstName(account.getAccountFirstName());
            }
            if(account.getAccountLastName()!=null){
                updateAccount.setAccountLastName(account.getAccountLastName());
            }
            if(account.getAddress()!=null){
                updateAccount.setAddress(account.getAddress());
            }
            if(account.getMobileNumber()!=null){
                updateAccount.setMobileNumber(account.getMobileNumber());
            }
              return updateAccount;
        }).flatMap(updateAccount -> accountRepository.save(updateAccount));
    }

    @Transactional
    public Mono<Void> deleteAccount(Long accountNumber) {
        return accountRepository.findById(accountNumber).flatMap(accountHolder -> accountRepository.delete(accountHolder));
    }
}
