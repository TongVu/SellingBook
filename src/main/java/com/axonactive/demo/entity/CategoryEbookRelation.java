package com.axonactive.demo.entity;

import com.axonactive.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@SqlResultSetMapping(
        name = "ebookHasRatingGreaterThan",
        classes = {
                @ConstructorResult(
                        targetClass = CategoryEbookRelationDto.class,
                        columns = {
                                @ColumnResult(name = "bookTitle", type = String.class),
                                @ColumnResult(name = "bookPage", type = Integer.class),
                                @ColumnResult(name = "rating", type = Integer.class),
                                @ColumnResult(name = "introduction", type = String.class),
                                @ColumnResult(name = "categoryName", type = String.class),
                                @ColumnResult(name = "publisherName", type = String.class) })})
@NamedNativeQuery(
        name = CategoryEbookRelation.FIND_EBOOK_BY_RATING,
        query = "SELECT e.title as bookTitle, e.page as bookPage, e.rating, e.introduction, c2.name as categoryName, p.name as publisherName " +
                "FROM ebook e, category_ebook_relation c, category c2, publisher p " +
                "WHERE (e.id = c.fk_ebook_id) " +
                    "AND (c2.id = c.fk_category_id) " +
                    "AND (p.id = e.fk_publisher_id) " +
                    "AND e.rating > ?1",
        resultSetMapping = "ebookHasRatingGreaterThan")

public class CategoryEbookRelation {
    public static final String FIND_EBOOK_BY_RATING = "CategoryEbookRelation.findEbookByRating";
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
