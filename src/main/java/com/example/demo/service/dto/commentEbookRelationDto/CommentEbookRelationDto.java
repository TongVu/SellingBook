package com.example.demo.service.dto.commentEbookRelationDto;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Ebook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEbookRelationDto {
    private Integer id;

    private Comment comment;

    private Ebook ebook;
}
