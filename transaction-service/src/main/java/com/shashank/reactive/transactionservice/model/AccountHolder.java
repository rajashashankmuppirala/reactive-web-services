package com.shashank.reactive.transactionservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table("tbl_account_holder")
public class AccountHolder {
    @Id
    private Long accountNumber;

    private String accountFirstName;

    private String accountLastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern="MM/dd/yyyy")
    private LocalDate dateOfBirth;

    private String accountType;

    private LocalDateTime accountStartDate;

    private BigDecimal accountBalance;

    private Long mobileNumber;

    private String address;

}
