package com.example.demo.service;

import com.example.demo.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAll();

    void save(Author author);

    void deleteById(Integer id);

    Optional<Author> findAuthorById(Integer id);
}
