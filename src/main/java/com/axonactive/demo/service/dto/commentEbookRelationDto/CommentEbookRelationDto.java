package com.axonactive.demo.service.dto.commentEbookRelationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEbookRelationDto {

    private String commentContent;

    private LocalDate date;

    private String accountName;

    private String ebookTitle;

}
