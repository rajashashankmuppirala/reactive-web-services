package com.shashank.reactive.withdrawservice.service;

import com.shashank.reactive.withdrawservice.model.TransactionDetailsDTO;
import com.shashank.reactive.withdrawservice.model.WithdrawDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WithdrawService {

    @Autowired
    private WebClient webClient;

    public Mono<TransactionDetailsDTO> withdraw(WithdrawDTO withdrawDTO){
       return webClient.post().uri("/withdraw").body(Mono.just(withdrawDTO), WithdrawDTO.class)
                .retrieve().bodyToMono(TransactionDetailsDTO.class);
    }

}
