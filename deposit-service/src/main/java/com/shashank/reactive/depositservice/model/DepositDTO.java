package com.shashank.reactive.depositservice.model;

import lombok.Data;

@Data
public class DepositDTO {

    private Long accountNumber;

    private Long depositAmount;

    private String depositType;


}
