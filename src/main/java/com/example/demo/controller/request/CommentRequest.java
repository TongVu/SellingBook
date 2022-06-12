package com.example.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private String commentContent;

    private Integer bookRating;

    private Integer commentUpvote;

    private LocalDate date;

    private Integer accountId;
}
