package com.axonactive.demo.service;

import com.axonactive.demo.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    List<Publisher> getAll();

    Publisher save(Publisher publisher);

    void deleteById(Integer id);

    Optional<Publisher> findPublisherById(Integer id);
}
