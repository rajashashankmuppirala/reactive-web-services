package com.shashank.reactive.transactionservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table("tbl_transaction")
public class Transaction implements Persistable<Long> {
    @Id
    private Long accountNumber;

    private BigDecimal depositAmount;

    private String depositType;

    private Long sourceAccountNumber;

    private LocalDateTime transactionDate;

    private BigDecimal withdrawAmount;

    private String withdrawType;

    private String transactionId;


    @Override
    public Long getId() {
        return getAccountNumber();
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
