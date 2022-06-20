package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.EbookRequest;
import com.axonactive.demo.entity.Publisher;
import com.axonactive.demo.exception.ResourceNotFoundException;
import com.axonactive.demo.repository.EbookRepository;
import com.axonactive.demo.service.EbookService;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.service.PublisherService;
import com.axonactive.demo.service.dto.ebookDto.EbookInfoCategoryAuthorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EbookServiceImpl implements EbookService {

    @Autowired
    private EbookRepository ebookRepository;

    @Autowired
    private PublisherService publisherService;

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
    public Ebook update(Ebook updatedEbook, EbookRequest ebookRequest) throws ResourceNotFoundException {
        Publisher requestedPublisher = publisherService.findPublisherById(ebookRequest.getPublisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found " + ebookRequest.getPublisherId()));

        updatedEbook.setPage(ebookRequest.getPage());
        updatedEbook.setTitle(ebookRequest.getTitle());
        updatedEbook.setRating(ebookRequest.getRating());
        updatedEbook.setIntroduction(ebookRequest.getIntroduction());
        updatedEbook.setPurchased(ebookRequest.getPurchased());
        updatedEbook.setViewLinkStatus(ebookRequest.getViewLinkStatus());
        updatedEbook.setPublisher(requestedPublisher);
        return ebookRepository.save(updatedEbook);
    }

    @Override
    public Ebook create(EbookRequest ebookRequest) throws ResourceNotFoundException {
        Publisher requestedPublisher = publisherService.findPublisherById(ebookRequest.getPublisherId())
                .orElseThrow(() -> new ResourceAccessException("Not found publisher " + ebookRequest.getPublisherId()));

        Ebook createdEbook = new Ebook();
        createdEbook.setPage(ebookRequest.getPage());
        createdEbook.setTitle(ebookRequest.getTitle());
        createdEbook.setRating(ebookRequest.getRating());
        createdEbook.setIntroduction(ebookRequest.getIntroduction());
        createdEbook.setPurchased(ebookRequest.getPurchased());
        createdEbook.setViewLinkStatus(ebookRequest.getViewLinkStatus());
        createdEbook.setPublisher(requestedPublisher);

        return ebookRepository.save(createdEbook);
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
