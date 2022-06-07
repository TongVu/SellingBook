package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class CommentEbookRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // relation with Comment
    @ManyToOne
    @JoinColumn(name = "fk_comment_id")
    private Comment comment;

    // relation with Ebook
    @ManyToOne
    @JoinColumn(name = "fk_ebook_id")
    private Ebook ebook;
}
