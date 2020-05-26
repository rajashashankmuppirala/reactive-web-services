package com.shashank.reactive.depositservice.service;

import com.shashank.reactive.depositservice.model.DepositDTO;
import com.shashank.reactive.depositservice.model.TransactionDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DepositService {

    @Autowired
    private WebClient webClient;

    public Mono<TransactionDetailsDTO> deposit(DepositDTO depositDTO){
       return webClient.post().uri("/deposit").body(Mono.just(depositDTO), DepositDTO.class)
                .retrieve().bodyToMono(TransactionDetailsDTO.class);
    }

}
