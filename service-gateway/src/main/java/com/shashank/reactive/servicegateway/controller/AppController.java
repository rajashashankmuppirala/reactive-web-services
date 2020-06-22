package com.shashank.reactive.servicegateway.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AppController {

    @GetMapping("/auth-details")
    @ResponseBody
    public Mono<String> getAuthDetails(
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal OidcUser oidcUser) {
        return Mono.just("Client: " + authorizedClient.getClientRegistration().getClientName() + " userName: "+ oidcUser.getName());
    }
}
