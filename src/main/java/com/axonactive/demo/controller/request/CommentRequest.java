package com.axonactive.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private String commentContent;

    @Min(0)
    @Max(5)
    private Integer bookRating;

    private Integer commentUpvote;

    private LocalDate date;

    private Integer accountId;
}
