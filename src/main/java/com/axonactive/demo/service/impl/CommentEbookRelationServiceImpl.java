package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.CommentEbookRelationRequest;
import com.axonactive.demo.entity.Comment;
import com.axonactive.demo.entity.CommentEbookRelation;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.repository.CommentEbookRelationRepository;
import com.axonactive.demo.service.CommentEbookRelationService;
import com.axonactive.demo.service.CommentService;
import com.axonactive.demo.service.EbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentEbookRelationServiceImpl implements CommentEbookRelationService {
    @Autowired
    private CommentEbookRelationRepository commentEbookRelationRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private EbookService ebookService;

    @Override
    public List<CommentEbookRelation> getAll() {
        return commentEbookRelationRepository.findAll();
    }

    @Override
    public CommentEbookRelation save(CommentEbookRelation category) {
        return commentEbookRelationRepository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        commentEbookRelationRepository.deleteById(id);
    }

    @Override
    public CommentEbookRelation update(CommentEbookRelation updatedCommentEbookRelation, CommentEbookRelationRequest commentEbookRelationRequest) {
        log.info("Searching for comment has id {} ", commentEbookRelationRequest.getCommentId());
        Comment requestedComment = commentService.findCommentById(commentEbookRelationRequest.getCommentId())
                .orElseThrow(BusinessLogicException::commenNotFound);

        log.info("Searching for ebook has id {} ", commentEbookRelationRequest.getEbookId());
        Ebook requetedEbook = ebookService.findEbookById(commentEbookRelationRequest.getEbookId())
                .orElseThrow(BusinessLogicException::ebookNotFound);

        updatedCommentEbookRelation.setComment(requestedComment);
        updatedCommentEbookRelation.setEbook(requetedEbook);

        return commentEbookRelationRepository.save(updatedCommentEbookRelation);
    }

    @Override
    public CommentEbookRelation create(CommentEbookRelationRequest commentEbookRelationRequest) {
        log.info("Searching for comment has id {} ", commentEbookRelationRequest.getCommentId());
        Comment comment = commentService.findCommentById(commentEbookRelationRequest.getCommentId())
                .orElseThrow(BusinessLogicException::commenNotFound);

        log.info("Searching for ebook has id {} ", commentEbookRelationRequest.getEbookId());
        Ebook ebook = ebookService.findEbookById(commentEbookRelationRequest.getEbookId())
                .orElseThrow(BusinessLogicException::ebookNotFound);

        CommentEbookRelation createdCommentEbookRelation = new CommentEbookRelation();
        createdCommentEbookRelation.setComment(comment);
        createdCommentEbookRelation.setEbook(ebook);

        return commentEbookRelationRepository.save(createdCommentEbookRelation);
    }

    @Override
    public Optional<CommentEbookRelation> findCommentEbookRelationById(Integer id) {
        return commentEbookRelationRepository.findById(id);
    }

    @Override
    public List<Comment> findCommentByEbookId(Integer id) {
        return commentEbookRelationRepository.findCommentByEbookId(id);
    }
}
