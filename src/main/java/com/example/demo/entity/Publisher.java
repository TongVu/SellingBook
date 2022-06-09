package com.example.demo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
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
