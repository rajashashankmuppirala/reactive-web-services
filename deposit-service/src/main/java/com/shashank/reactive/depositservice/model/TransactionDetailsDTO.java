package com.shashank.reactive.depositservice.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransactionDetailsDTO {
    String transactionId;
    String status;
    LocalDateTime transactionDate;
    String transactionType;
    String transactionAmount;


}
