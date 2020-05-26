package com.shashank.reactive.depositservice.controller;

import com.shashank.reactive.depositservice.model.DepositDTO;
import com.shashank.reactive.depositservice.model.TransactionDetailsDTO;
import com.shashank.reactive.depositservice.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/deposit-service")
public class DepositController {

    @Autowired
    DepositService depositService;


    @PostMapping(value = "/deposit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDetailsDTO> deposit(@RequestBody DepositDTO depositDTO){
         return depositService.deposit(depositDTO);
    }


}
