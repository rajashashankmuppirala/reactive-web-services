package com.shashank.reactive.servicegateway.config;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.cloud.gateway.filter.WebClientHttpRoutingFilter;
import org.springframework.cloud.gateway.filter.WebClientWriteResponseFilter;
import org.springframework.cloud.gateway.filter.headers.HttpHeadersFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${default-client-registration-id}")
    private String defaultClientRegistrationId;

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
        return http.oauth2Client().and()
                .csrf().disable().build();
    }


    @Bean
    public WebClientHttpRoutingFilter webClientHttpRoutingFilter(WebClient webClient, ObjectProvider<List<HttpHeadersFilter>> headersFilters) {
        return new WebClientHttpRoutingFilter(webClient, headersFilters);
    }

    @Bean
    public WebClientWriteResponseFilter webClientWriteResponseFilter() {
        return new WebClientWriteResponseFilter();
    }

    @Bean
    WebClient webClient(ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth.setDefaultClientRegistrationId(defaultClientRegistrationId);
        return WebClient.builder()
                .filter(oauth)
                .build();

    }

    @Bean
    public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(
            ReactiveClientRegistrationRepository clientRegistrationRepository,
            ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {

        ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider =
                ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                        .password()
                        .authorizationCode()
                        .refreshToken()
                        .build();

        DefaultReactiveOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultReactiveOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        authorizedClientManager.setContextAttributesMapper(contextAttributesMapper());

        return authorizedClientManager;
    }


    private Function<OAuth2AuthorizeRequest, Mono<Map<String, Object>>> contextAttributesMapper() {
        return authorizeRequest -> {
            Map<String, Object> contextAttributes = Collections.emptyMap();
            ServerWebExchange serverWebExchange = authorizeRequest.getAttribute(ServerWebExchange.class.getName());
            String userName = "";
            String password = "";

            String authHeader =  serverWebExchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (null != authHeader && authHeader.contains("Basic")) {
                String base64Credentials = authHeader.substring("Basic".length()).trim();
                if (null!= base64Credentials && !base64Credentials.equals("")){
                    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
                    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
                    final String[] values = credentials.split(":", 2);
                    userName = values[0];
                    password = values[1];
                }

            }

            if (StringUtils.hasText(userName) && StringUtils.hasText(password)) {
                contextAttributes = new HashMap<>();
                contextAttributes.put(OAuth2AuthorizationContext.USERNAME_ATTRIBUTE_NAME, userName);
                contextAttributes.put(OAuth2AuthorizationContext.PASSWORD_ATTRIBUTE_NAME, password);
            }
            return Mono.just(contextAttributes);
        };
    }
}
