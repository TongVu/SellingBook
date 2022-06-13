package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class CategoryEbookRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // relation with Ebook
    @ManyToOne
    @JoinColumn(name = "fk_ebook_id")
    private Ebook ebook;

    // relation with Category
    @ManyToOne
    @JoinColumn(name = "fk_category_id")
    private Category category;

}
