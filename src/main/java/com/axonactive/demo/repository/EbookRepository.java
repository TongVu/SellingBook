package com.axonactive.demo.repository;

import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.service.dto.ebookDto.EbookInfoCategoryAuthorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Integer> {
    @Query("SELECT new com.axonactive.demo.service.dto.ebookDto.EbookInfoCategoryAuthorDto(e.title, e.page, e.rating, e.introduction, e.purchased, p.name, p.email, c.name, (a.firstName || ' ' || a.lastName) as authorFullName) " +
            "FROM Ebook e, EbookAuthorRelation ear, Author a, CategoryEbookRelation cer, Category c, Publisher p " +
            "WHERE p.id = e.publisher.id AND " +
            "e.id = ear.ebook.id AND " +
            "ear.author.id = a.id AND " +
            "cer.ebook.id = e.id AND " +
            "cer.category.id = c.id AND " +
            "e.page > ?1 ")
    List<EbookInfoCategoryAuthorDto> findEbookByPagesGreaterThan(Integer pages);

}
