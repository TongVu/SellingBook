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
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDate dateAdded;

    @NotNull
    @Min(0)
    private double ebookPrice;

    @ManyToOne
    @JoinColumn(name = "fk_invoice_id")
    private Invoice invoice;


    @ManyToOne
    @JoinColumn(name = "fk_ebook_id")
    private Ebook ebook;
}
