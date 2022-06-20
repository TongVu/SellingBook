package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.CreditCardRequest;
import com.axonactive.demo.entity.CreditCard;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.CreditCardService;
import com.axonactive.demo.service.dto.creditCardDto.CreditCardDto;
import com.axonactive.demo.service.mapper.CreditCardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(CreditCardResource.PATH)
public class CreditCardResource {
    public static final String PATH = "/api/creditcards";

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    CreditCardMapper creditCardMapper;

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<CreditCardDto>> getAll() {
        return ResponseEntity.ok(creditCardMapper.toDtos(creditCardService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardDto> getById(@PathVariable("id") Integer id) {
        log.info("Searching for credit card has id {} ", id);
        CreditCard foundCreditCard = creditCardService.findCreditCardById(id)
                .orElseThrow(BusinessLogicException::creditCardNotFound);

        return ResponseEntity.ok(creditCardMapper.toDto(foundCreditCard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCardDto> update(@PathVariable("id") Integer id,
                                                @RequestBody CreditCardRequest creditCardRequest) {
        log.info("Searching for credit card has id {} ", id);
        CreditCard updatedCreditCard = creditCardService.findCreditCardById(id)
                .orElseThrow(BusinessLogicException::creditCardNotFound);

        return ResponseEntity.ok(creditCardMapper.toDto(creditCardService.update(updatedCreditCard, creditCardRequest)));
    }

    @PostMapping
    public ResponseEntity<CreditCardDto> create(@RequestBody CreditCardRequest creditCardRequest) {
        CreditCard createdCreditCard = creditCardService.create(creditCardRequest);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdCreditCard.getId()))
                .body(creditCardMapper.toDto(createdCreditCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        log.info("Searching for credit card has id {} ", id);
        CreditCard deletedCreditCard = creditCardService.findCreditCardById(id)
                .orElseThrow(BusinessLogicException::creditCardNotFound);
        creditCardService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}