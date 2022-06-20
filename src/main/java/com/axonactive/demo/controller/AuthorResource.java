package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.AuthorRequest;
import com.axonactive.demo.entity.Author;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.AuthorService;
import com.axonactive.demo.service.dto.authorDto.AuthorDto;
import com.axonactive.demo.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
        Author author = authorService.findAuthorById(id)
                .orElseThrow(BusinessLogicException::authorNotFound);

        return ResponseEntity.ok(authorMapper.toDto(author));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Integer id,
                                                  @RequestBody AuthorRequest author) {
        Author updatedAuthor = authorService.findAuthorById(id)
                .orElseThrow(BusinessLogicException::authorNotFound);

        updatedAuthor.setDob(author.getDob());
        updatedAuthor.setAddress(author.getAddress());
        updatedAuthor.setGender(author.getGender());
        updatedAuthor.setFirstName(author.getFirstName());
        updatedAuthor.setLastName(author.getLastName());
        updatedAuthor.setEmail(author.getEmail());
        updatedAuthor.setPhone(author.getPhone());
        updatedAuthor.setNationality(author.getNationality());

        return ResponseEntity.ok(authorMapper.toDto(authorService.save(updatedAuthor)));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorRequest author) {
        Author createdAuthor = new Author();
        createdAuthor.setDob(author.getDob());
        createdAuthor.setAddress(author.getAddress());
        createdAuthor.setGender(author.getGender());
        createdAuthor.setFirstName(author.getFirstName());
        createdAuthor.setLastName(author.getLastName());
        createdAuthor.setEmail(author.getEmail());
        createdAuthor.setPhone(author.getPhone());
        createdAuthor.setNationality(author.getNationality());

        authorService.save(createdAuthor);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdAuthor.getId()))
                .body(authorMapper.toDto(createdAuthor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        Author deletedAuthor = authorService.findAuthorById(id)
                .orElseThrow(BusinessLogicException::authorNotFound);
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
