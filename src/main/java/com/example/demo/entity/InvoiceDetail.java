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
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDate dateAdded;

    @NotNull
    private double ebookPrice;

    @ManyToOne
    @JoinColumn(name = "fk_invoice_id")
    private Invoice invoice;


    @ManyToOne
    @JoinColumn(name = "fk_ebook_id")
    private Ebook ebook;
}
