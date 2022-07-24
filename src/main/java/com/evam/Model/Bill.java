package com.evam.Model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bill", schema = "public")
public class Bill {

    private static final long serialVersionUID = -3068197585300003752L;

    @Id
    @SequenceGenerator(name="identifier", sequenceName="mytable_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
    @Column(name = "id")
    Long id;

    @Column(name = "bill_number")
    private String billNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer cutomerId;

    @Column(name = "amount_of_bill")
    private Double amountOfBill;

    @Column(name = "bill_process_date")
    private LocalDate billProcessDate;
}

    