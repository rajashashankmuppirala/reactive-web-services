package com.shashank.reactive.accountservice.service;

import com.shashank.reactive.accountservice.model.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    @Autowired
    private WebClient webClient;

    public Flux<AccountHolder> getAllAccounts() {
        return webClient.get().uri("/accounts").retrieve().bodyToFlux(AccountHolder.class);
    }

    public Mono<AccountHolder> getaccountById(Long accountNumber) {
        return webClient.get().uri(uriBuilder -> uriBuilder
               .path("/account").queryParam("accountNumber", accountNumber)
               .build()).retrieve().bodyToMono(AccountHolder.class);
    }

    public Mono<AccountHolder> createAccount(AccountHolder account) {
       return webClient.post().uri("/account").body(Mono.just(account), AccountHolder.class)
               .retrieve().bodyToMono(AccountHolder.class);
    }

    public Mono<AccountHolder> updateAccount(AccountHolder account) {
       return webClient.put().uri("/account").body(Mono.just(account),AccountHolder.class)
               .retrieve().bodyToMono(AccountHolder.class);
    }

    public Mono<Void> deleteAccount(Long accountNumber) {
        return webClient.delete().uri(uriBuilder -> uriBuilder
                .path("/account").queryParam("accountNumber", accountNumber)
                .build()).retrieve().bodyToMono(Void.class);
    }
}
