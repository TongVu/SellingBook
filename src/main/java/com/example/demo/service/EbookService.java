package com.example.demo.service;

import com.example.demo.entity.Ebook;

import java.util.List;
import java.util.Optional;

public interface EbookService {

    List<Ebook> getAll();

    void save(Ebook ebook);

    void deleteById(Integer id);

    Optional<Ebook> findEbookById(Integer id);
}
