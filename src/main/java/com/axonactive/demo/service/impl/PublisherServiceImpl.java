package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.PublisherRequest;
import com.axonactive.demo.entity.Publisher;
import com.axonactive.demo.repository.PublisherRepository;
import com.axonactive.demo.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
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
    public Publisher update(Publisher publisher, PublisherRequest publisherRequest) {
        publisher.setName(publisherRequest.getName());
        publisher.setPhone(publisherRequest.getPhone());
        publisher.setAddress(publisherRequest.getAddress());
        publisher.setEmail(publisherRequest.getEmail());

        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher create(PublisherRequest publisherRequest) {
        Publisher createdPublisher = new Publisher();
        createdPublisher.setName(publisherRequest.getName());
        createdPublisher.setPhone(publisherRequest.getPhone());
        createdPublisher.setAddress(publisherRequest.getAddress());
        createdPublisher.setEmail(publisherRequest.getEmail());

        return publisherRepository.save(createdPublisher);
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
