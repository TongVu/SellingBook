package com.axonactive.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class CommentEbookRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "fk_ebook_id")
    private Ebook ebook;
}
