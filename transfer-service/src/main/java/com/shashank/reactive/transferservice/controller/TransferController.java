package com.shashank.reactive.transferservice.controller;

import com.shashank.reactive.transferservice.model.TransactionDetailsDTO;
import com.shashank.reactive.transferservice.model.TransferDTO;
import com.shashank.reactive.transferservice.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transfer-service")
public class TransferController {

    @Autowired
    TransferService transferService;


    @PostMapping(value = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDetailsDTO> deposit(@RequestBody TransferDTO transferDTO){
        return transferService.deposit(transferDTO);
    }
}
