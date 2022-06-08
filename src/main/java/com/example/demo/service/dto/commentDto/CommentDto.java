package com.example.demo.service.dto.commentDto;

import com.example.demo.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Integer id;

    private String commentContent;

    private Integer bookRating;

    private Integer commentUpvote;

    private LocalDate date;

    private Account account;
}
