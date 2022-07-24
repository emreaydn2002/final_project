package com.evam.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = -7730696724966768527L;

    private Long id;
    private String customerName;
    private String customerSurname;
    private String subscriberNumber;
}