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
public class EbookAuthorRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // relation with Ebook
    @ManyToOne
    @JoinColumn(name = "fk_ebook_id")
    private Ebook ebook;

    // relation with Author
    @ManyToOne
    @JoinColumn(name = "fk_author_id")
    private Author author;
}
