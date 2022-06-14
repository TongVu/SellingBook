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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(length = 2000)
    private String commentContent;

    private Integer bookRating;

    private Integer commentUpvote;

    @NotNull
    private LocalDate date;

    // relation with usrAccount
    @ManyToOne
    @JoinColumn(name = "fk_account_id")
    private Account account;
}
