package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.AuthorRequest;
import com.axonactive.demo.entity.Author;
import com.axonactive.demo.repository.AuthorRepository;
import com.axonactive.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void update(Author updatedAuthor, AuthorRequest authorRequest) {

        updatedAuthor.setDob(authorRequest.getDob());
        updatedAuthor.setAddress(authorRequest.getAddress());
        updatedAuthor.setGender(authorRequest.getGender());
        updatedAuthor.setFirstName(authorRequest.getFirstName());
        updatedAuthor.setLastName(authorRequest.getLastName());
        updatedAuthor.setEmail(authorRequest.getEmail());
        updatedAuthor.setPhone(authorRequest.getPhone());
        updatedAuthor.setNationality(authorRequest.getNationality());

        authorRepository.save(updatedAuthor);
    }


    @Override
    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author create(AuthorRequest authorRequest) {
        Author createdAuthor = new Author();
        createdAuthor.setDob(authorRequest.getDob());
        createdAuthor.setAddress(authorRequest.getAddress());
        createdAuthor.setGender(authorRequest.getGender());
        createdAuthor.setFirstName(authorRequest.getFirstName());
        createdAuthor.setLastName(authorRequest.getLastName());
        createdAuthor.setEmail(authorRequest.getEmail());
        createdAuthor.setPhone(authorRequest.getPhone());
        createdAuthor.setNationality(authorRequest.getNationality());

        authorRepository.save(createdAuthor);

        return createdAuthor;
    }

    @Override
    public Optional<Author> findAuthorById(Integer id) {
        return authorRepository.findById(id);
    }
}
