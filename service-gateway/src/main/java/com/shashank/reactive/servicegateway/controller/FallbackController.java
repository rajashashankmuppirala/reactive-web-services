package com.shashank.reactive.servicegateway.controller;

import com.shashank.reactive.servicegateway.model.FallbackResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/accountService")
    public FallbackResponse getFallAccountService() {
        FallbackResponse fb = new FallbackResponse();
        fb.setStatus(502);
        fb.setDescription("account service fall back message");
        return fb;
    }


    @GetMapping("/depositService")
    public FallbackResponse getFallDepositService() {
        FallbackResponse fb = new FallbackResponse();
        fb.setStatus(502);
        fb.setDescription("deposit service fall back message");
        return fb;
    }

    @GetMapping("/transferService")
    public FallbackResponse getFallTransferServiceService() {
        FallbackResponse fb = new FallbackResponse();
        fb.setStatus(502);
        fb.setDescription("transfer service fall back message");
        return fb;
    }

    @GetMapping("/withdrawService")
    public FallbackResponse getWithdrawService() {
        FallbackResponse fb = new FallbackResponse();
        fb.setStatus(502);
        fb.setDescription("withdraw service fall back message");
        return fb;
    }
}
