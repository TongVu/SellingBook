package com.axonactive.demo.service;

import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.service.dto.ebookDto.EbookInfoCategoryAuthorDto;

import java.util.List;
import java.util.Optional;

public interface EbookService {
    List<Ebook> getAll();

    Ebook save(Ebook ebook);

    void deleteById(Integer id);

    Optional<Ebook> findEbookById(Integer id);

    List<EbookInfoCategoryAuthorDto> findEbookByPagesGreaterThan(Integer pages);
}
