package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.CommentEbookRelationRequest;
import com.axonactive.demo.entity.Comment;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.exception.ResourceNotFoundException;
import com.axonactive.demo.repository.CommentEbookRelationRepository;
import com.axonactive.demo.repository.CommentRepository;
import com.axonactive.demo.repository.EbookRepository;
import com.axonactive.demo.service.CommentEbookRelationService;
import com.axonactive.demo.entity.CommentEbookRelation;
import com.axonactive.demo.service.CommentService;
import com.axonactive.demo.service.EbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

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
    public CommentEbookRelation update(CommentEbookRelation updatedCommentEbookRelation, CommentEbookRelationRequest commentEbookRelationRequest) throws ResourceNotFoundException{
        Comment requestedComment = commentService.findCommentById(commentEbookRelationRequest.getCommentId())
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found " + commentEbookRelationRequest.getCommentId()));

        Ebook requetedEbook = ebookService.findEbookById(commentEbookRelationRequest.getEbookId())
                .orElseThrow(() -> new ResourceNotFoundException("Ebook not found" + commentEbookRelationRequest.getEbookId()));

        updatedCommentEbookRelation.setComment(requestedComment);
        updatedCommentEbookRelation.setEbook(requetedEbook);

        return commentEbookRelationRepository.save(updatedCommentEbookRelation);
    }

    @Override
    public CommentEbookRelation create(CommentEbookRelationRequest commentEbookRelationRequest) throws ResourceNotFoundException{

        Comment comment = commentService.findCommentById(commentEbookRelationRequest.getCommentId())
                .orElseThrow(() -> new ResourceAccessException("Comment not found " + commentEbookRelationRequest.getCommentId()));

        Ebook ebook = ebookService.findEbookById(commentEbookRelationRequest.getEbookId())
                .orElseThrow(() -> new ResourceAccessException("Ebook not found " + commentEbookRelationRequest.getEbookId()));

        CommentEbookRelation createdCommentEbookRelation = new CommentEbookRelation();
        createdCommentEbookRelation.setComment(comment);
        createdCommentEbookRelation.setEbook(ebook);

        return commentEbookRelationRepository.save(createdCommentEbookRelation);
    }

    @Override
    public Optional<CommentEbookRelation> findCommentEbookRelationById(Integer id) {
        return commentEbookRelationRepository.findById(id);
    }
}
