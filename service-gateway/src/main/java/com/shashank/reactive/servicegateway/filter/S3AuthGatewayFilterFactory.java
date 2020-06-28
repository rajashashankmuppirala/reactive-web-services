package com.shashank.reactive.servicegateway.filter;

import com.shashank.reactive.servicegateway.auth.s3.AWS4SignerBase;
import com.shashank.reactive.servicegateway.auth.s3.S3SignerForAuthorizationHeader;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;


@Component
@Slf4j
public class S3AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<S3AuthGatewayFilterFactory.Config> {

    public S3AuthGatewayFilterFactory() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(S3AuthGatewayFilterFactory.Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            log.info(request.getPath().value());
            Route s3uri = exchange.getAttribute(GATEWAY_ROUTE_ATTR);

            S3SignerForAuthorizationHeader a = new S3SignerForAuthorizationHeader(URI.create(s3uri.getUri().toString() +
                    exchange.getRequest().getPath().value()),request.getMethodValue(),config.getService(),config.getRegion());
            byte[] dta = AWS4SignerBase.hash("");
            String decoded = Hex.encodeHexString(dta);
            Map<String,String> queryParams = new HashMap<String, String>();
            HttpHeaders headers = a.computeSignature(queryParams,decoded, config.getAccessKey(),config.getSecret());
            exchange.getRequest().mutate().headers(httpHeaders -> {
                httpHeaders.addAll(headers);
                httpHeaders.replace(HttpHeaders.HOST, Arrays.asList(s3uri.getUri().getHost()));
            }).build();

         return chain.filter(exchange);
        };
    }

    @Data
    public static class Config {
        private String service;
        private String accessKey;
        private String secret;
        private String region;
    }
}
