package com.example.demo.entity;

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
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer cardNumber;

    @NotNull
    private LocalDate expiredDate;

    @NotNull
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "fk_account_id")
    private Account account;
}
