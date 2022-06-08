package com.example.demo.service.impl;

import com.example.demo.entity.Ebook;
import com.example.demo.repository.EbookRepository;
import com.example.demo.service.EbookService;
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
    public void save(Ebook ebook) {
        ebookRepository.save(ebook);
    }

    @Override
    public void deleteById(Integer id) {
        ebookRepository.deleteById(id);
    }

    @Override
    public Optional<Ebook> findEbookById(Integer id) {
        return ebookRepository.findById(id);
    }
}
