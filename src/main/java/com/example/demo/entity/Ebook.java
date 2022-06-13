package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Ebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer page;

    @NotNull
    private String title;

    @NotNull
    private Double rating;

    @NotNull
    @Column(length = 10000)
    private String introduction;

    @NotNull
    private Boolean purchased;

    @NotNull
    private String viewLinkStatus;

    @ManyToOne
    @JoinColumn(name = "fk_publisher_id")
    private Publisher publisher;
}
