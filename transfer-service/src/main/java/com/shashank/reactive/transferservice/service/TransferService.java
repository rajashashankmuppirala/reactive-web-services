package com.shashank.reactive.transferservice.service;


import com.shashank.reactive.transferservice.model.TransactionDetailsDTO;
import com.shashank.reactive.transferservice.model.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TransferService {

    @Autowired
    private WebClient webClient;

    public Mono<TransactionDetailsDTO> deposit(TransferDTO transferDTO){
       return webClient.post().uri("/transfer").body(Mono.just(transferDTO), TransferDTO.class)
                .retrieve().bodyToMono(TransactionDetailsDTO.class);
    }

}
