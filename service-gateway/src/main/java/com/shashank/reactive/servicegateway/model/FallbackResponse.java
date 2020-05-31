package com.shashank.reactive.servicegateway.model;

import lombok.Data;

@Data
public class FallbackResponse {

    private int status;
    private String description;
}
