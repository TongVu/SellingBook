package com.axonactive.demo.service;

import com.axonactive.demo.controller.request.PublisherRequest;
import com.axonactive.demo.entity.Publisher;
import com.axonactive.demo.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    List<Publisher> getAll();

    Publisher save(Publisher publisher);

    Publisher update(Publisher publisher, PublisherRequest publisherRequest) throws ResourceNotFoundException;

    Publisher create(PublisherRequest publisherRequest);

    void deleteById(Integer id);

    Optional<Publisher> findPublisherById(Integer id);
}
