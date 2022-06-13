package com.example.demo.controller;

import com.example.demo.controller.request.AuthorRequest;
import com.example.demo.entity.Author;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.AuthorService;
import com.example.demo.service.dto.authorDto.AuthorDto;
import com.example.demo.service.mapper.AuthorMapper;
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
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Author author = authorService.findAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        return ResponseEntity.ok(authorMapper.toDto(author));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Integer id,
                                                  @RequestBody AuthorRequest author) throws ResourceNotFoundException {
        Author updatedAuthor = authorService.findAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        updatedAuthor.setAddress(author.getAddress());
        updatedAuthor.setDob(author.getDob());
        updatedAuthor.setGender(author.getGender());
        updatedAuthor.setEmail(author.getEmail());
        updatedAuthor.setNationality(author.getNationality());
        updatedAuthor.setPhone(author.getPhone());
        updatedAuthor.setFirstName(author.getFirstName());
        updatedAuthor.setLastName(author.getLastName());

        return ResponseEntity.ok(authorMapper.toDto(authorService.save(updatedAuthor)));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorRequest author) {
        Author createdAuthor = authorService.save(
                new Author(null,
                        author.getDob(),
                        author.getAddress(),
                        author.getGender(),
                        author.getFirstName(),
                        author.getLastName(),
                        author.getEmail(),
                        author.getPhone(),
                        author.getNationality()
                )
        );

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdAuthor.getId()))
                .body(authorMapper.toDto(createdAuthor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Author deletedAuthor = authorService.findAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
