package com.shashank.reactive.accountservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AccountHolder {
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
