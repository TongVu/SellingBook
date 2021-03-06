package com.axonactive.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
    @Column(unique = true)
    private Integer cardNumber;

    @NotNull
    private LocalDate expiredDate;

    @NotNull
    @Min(0)
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "fk_account_id")
    private Account account;
}
