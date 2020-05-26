package com.shashank.reactive.transactionservice.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDetailsDTO {
    String transactionId;
    String status;
    LocalDateTime transactionDate;
    String transactionType;
    BigDecimal transactionAmount;


}
