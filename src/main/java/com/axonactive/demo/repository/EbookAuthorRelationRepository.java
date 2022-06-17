package com.axonactive.demo.repository;

import com.axonactive.demo.entity.EbookAuthorRelation;
import com.axonactive.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto;
import com.axonactive.demo.service.dto.ebookDto.EbookInfoCategoryAuthorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EbookAuthorRelationRepository extends JpaRepository<EbookAuthorRelation, Integer> {
    List<EbookAuthorRelation> findByAuthorLastNameContainingIgnoreCase(String authorname);

    @Query("SELECT new com.axonactive.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto(e.title, e.page, e.introduction, e.publisher.name, (a.firstName || ' ' || a.lastName), a.email, a.gender) " +
            "FROM EbookAuthorRelation ea, Ebook e, Author a " +
            "WHERE ea.ebook.id = e.id AND ea.author.id = a.id AND e.publisher.name = ?1 ")
    List<EbookAuthorRelationDto> findEbooksByPublisher(String publisherName);

}
