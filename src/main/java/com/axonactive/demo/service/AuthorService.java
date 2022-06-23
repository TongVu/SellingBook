package com.axonactive.demo.service;

import com.axonactive.demo.controller.request.AuthorRequest;
import com.axonactive.demo.entity.Author;
import com.axonactive.demo.exception.BusinessLogicException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAll();

    Author save(Author author);


    Author update(Author updatedAuthor, AuthorRequest authorRequest) ;

    void deleteById(Integer id);

    Author create(AuthorRequest authorRequest);

    Optional<Author> findAuthorById(Integer id);
}
