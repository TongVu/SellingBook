package com.example.demo.service.impl;

import com.example.demo.entity.CommentEbookRelation;
import com.example.demo.repository.CommentEbookRelationRepository;
import com.example.demo.service.CommentEbookRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentEbookRelationServiceImpl implements CommentEbookRelationService {
    @Autowired
    private final CommentEbookRelationRepository commentEbookRelationRepository;

    @Override
    public List<CommentEbookRelation> getAll() {
        return commentEbookRelationRepository.findAll();
    }

    @Override
    public void save(CommentEbookRelation category) {
        commentEbookRelationRepository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        commentEbookRelationRepository.deleteById(id);
    }

    @Override
    public Optional<CommentEbookRelation> findCommentEbookRelationById(Integer id) {
        return commentEbookRelationRepository.findById(id);
    }
}
