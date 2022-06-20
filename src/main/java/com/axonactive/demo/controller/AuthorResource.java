package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.AuthorRequest;
import com.axonactive.demo.entity.Author;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.AuthorService;
import com.axonactive.demo.service.dto.authorDto.AuthorDto;
import com.axonactive.demo.service.mapper.AuthorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(AuthorResource.PATH)
public class AuthorResource {
    public static final String PATH = "/api/authors";

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorMapper authorMapper;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll() {
        return ResponseEntity.ok(authorMapper.toDtos(authorService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") Integer id) {
        log.info("Searching for author has id {} ", id);
        Author author = authorService.findAuthorById(id)
                .orElseThrow(BusinessLogicException::authorNotFound);

        return ResponseEntity.ok(authorMapper.toDto(author));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Integer id,
                                                  @RequestBody AuthorRequest author) {
        log.info("Searching for author has id {} ", id);
        Author updatedAuthor = authorService.findAuthorById(id)
                .orElseThrow(BusinessLogicException::authorNotFound);

        authorService.update(updatedAuthor, author);

        return ResponseEntity.ok(authorMapper.toDto(authorService.save(updatedAuthor)));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorRequest author) {
        Author createdAuthor = authorService.create(author);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdAuthor.getId()))
                .body(authorMapper.toDto(createdAuthor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        log.info("Searching for author has id {} ", id);
        Author deletedAuthor = authorService.findAuthorById(id)
                .orElseThrow(BusinessLogicException::authorNotFound);
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
