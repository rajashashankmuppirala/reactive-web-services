package com.shashank.reactive.transferservice.model;

import lombok.Data;

@Data
public class TransferDTO {

    private Long accountNumber;

    private Long depositAmount;

    private String depositType;

    private Long sourceAccountNumber;


}
