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
public class EbookAuthorRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_ebook_id")
    private Ebook ebook;

    @ManyToOne
    @JoinColumn(name = "fk_author_id")
    private Author author;
}
