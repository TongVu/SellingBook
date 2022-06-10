package com.example.demo.service;

import com.example.demo.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    List<Publisher> getAll();

    Publisher save(Publisher publisher);

    void deleteById(Integer id);

    Optional<Publisher> findPublisherById(Integer id);
}
