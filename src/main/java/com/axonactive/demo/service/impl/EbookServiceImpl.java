package com.axonactive.demo.service.impl;

import com.axonactive.demo.repository.EbookRepository;
import com.axonactive.demo.service.EbookService;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.service.dto.ebookDto.EbookInfoCategoryAuthorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EbookServiceImpl implements EbookService {

    @Autowired
    private final EbookRepository ebookRepository;

    @Override
    public List<Ebook> getAll() {
        return ebookRepository.findAll();
    }

    @Override
    public Ebook save(Ebook ebook) {
        return ebookRepository.save(ebook);
    }

    @Override
    public void deleteById(Integer id) {
        ebookRepository.deleteById(id);
    }

    @Override
    public Optional<Ebook> findEbookById(Integer id) {
        return ebookRepository.findById(id);
    }

    @Override
    public List<EbookInfoCategoryAuthorDto> findEbookByPagesGreaterThan(Integer pages) {
        return ebookRepository.findEbookByPagesGreaterThan(pages);
    }
}
