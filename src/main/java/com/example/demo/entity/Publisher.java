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
public class Publisher {
    @Id
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private String address;

    @NotNull
    private String email;
}
