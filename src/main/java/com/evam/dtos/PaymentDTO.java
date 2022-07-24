package com.evam.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PaymentDTO implements Serializable {

    private static final long serialVersionUID = -4932182578882888782L;

    private Long id;
    private Double amount;
    private CustomerDTO customerId;
    private LocalDate paymentProcessDate;
    private BillDTO billId;
}