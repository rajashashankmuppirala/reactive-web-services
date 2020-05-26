package com.shashank.reactive.withdrawservice.model;

import lombok.Data;

@Data
public class WithdrawDTO {

    private Long accountNumber;

    private Long withdrawAmount;

    private String withdrawType;


}
