package com.example.demo.service.dto.commentEbookRelationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEbookRelationDto {

//    private Comment comment;
    private String commentContent;

    private LocalDate date;

//   account.firstName + account.lastName
    private String accountName;

//    private Ebook ebook;
    private String ebookTitle;

}
