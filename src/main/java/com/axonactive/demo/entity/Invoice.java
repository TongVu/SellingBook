package com.axonactive.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDate invoiceDate;

    @NotNull
    private Integer quantity;

    @NotNull
    private boolean isPay;

    @NotNull
    private double totalPayment;

    @ManyToOne
    @JoinColumn(name = "fk_creditCard_id")
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = "fk_account_id")
    private Account account;

}
