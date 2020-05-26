package com.shashank.reactive.withdrawservice.controller;

import com.shashank.reactive.withdrawservice.model.TransactionDetailsDTO;
import com.shashank.reactive.withdrawservice.model.WithdrawDTO;
import com.shashank.reactive.withdrawservice.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/withdraw-service")
public class WithdrawController {


    @Autowired
    WithdrawService withdrawService;


    @PostMapping(value = "/withdraw", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDetailsDTO> deposit(@RequestBody WithdrawDTO withdrawDTO){
        return withdrawService.withdraw(withdrawDTO);
    }
}
