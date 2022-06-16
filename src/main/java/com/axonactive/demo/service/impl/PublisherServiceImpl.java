package com.axonactive.demo.service.impl;

import com.axonactive.demo.repository.PublisherRepository;
import com.axonactive.demo.entity.Publisher;
import com.axonactive.demo.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private final PublisherRepository publisherRepository;

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public void deleteById(Integer id) {
        publisherRepository.deleteById(id);
    }

    @Override
    public Optional<Publisher> findPublisherById(Integer id) {
        return publisherRepository.findById(id);
    }
}