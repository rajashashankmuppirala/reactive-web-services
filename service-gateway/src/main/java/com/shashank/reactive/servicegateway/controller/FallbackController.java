package com.shashank.reactive.servicegateway.controller;

import com.shashank.reactive.servicegateway.model.FallbackResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/accountService")
    public Mono<FallbackResponse> getFallAccountService() {
        FallbackResponse fb = new FallbackResponse();
        fb.setStatus(502);
        fb.setDescription("account service fall back message");
        return Mono.just(fb);
    }


    @GetMapping("/depositService")
    public Mono<FallbackResponse> getFallDepositService() {
        FallbackResponse fb = new FallbackResponse();
        fb.setStatus(502);
        fb.setDescription("deposit service fall back message");
        return Mono.just(fb);
    }

    @GetMapping("/transferService")
    public Mono<FallbackResponse> getFallTransferServiceService() {
        FallbackResponse fb = new FallbackResponse();
        fb.setStatus(502);
        fb.setDescription("transfer service fall back message");
        return Mono.just(fb);
    }

    @GetMapping("/withdrawService")
    public Mono<FallbackResponse> getWithdrawService() {
        FallbackResponse fb = new FallbackResponse();
        fb.setStatus(502);
        fb.setDescription("withdraw service fall back message");
        return Mono.just(fb);
    }
}
