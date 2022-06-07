package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
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
    private Integer rating;

    @NotNull
    private String introduction;

    @NotNull
    private Boolean purchased;

    @NotNull
    private String viewLinkStatus;

    @ManyToOne
    @JoinColumn(name = "fk_publisher_id")
    private Publisher publisher;
}
