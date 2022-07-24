package com.evam.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer", schema = "public")
public class Customer {

    private static final long serialVersionUID = 8036849806134231488L;

    @Id
    @SequenceGenerator(name="identifier", sequenceName="mytable_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_surname")
    private String customerSurname;

    @Column(name = "subscriber_number")
    private String subscriberNumber;

}

    