package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.CreditCardRequest;
import com.axonactive.demo.exception.ResourceNotFoundException;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.mapper.CreditCardMapper;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.entity.CreditCard;
import com.axonactive.demo.service.CreditCardService;
import com.axonactive.demo.service.dto.creditCardDto.CreditCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<CreditCardDto> getById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        CreditCard foundCreditCard = creditCardService.findCreditCardById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found " + id));

        return ResponseEntity.ok(creditCardMapper.toDto(foundCreditCard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCardDto> update(@PathVariable("id") Integer id,
                                                @RequestBody CreditCardRequest creditCardRequest) throws ResourceNotFoundException {
        CreditCard updatedCreditCard = creditCardService.findCreditCardById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found " + id));
        Account requestedAccount = accountService.findAccountById(creditCardRequest.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found " + creditCardRequest.getAccountId()));

        updatedCreditCard.setAccount(requestedAccount);
        updatedCreditCard.setCardNumber(creditCardRequest.getCardNumber());
        updatedCreditCard.setExpiredDate(creditCardRequest.getExpiredDate());
        updatedCreditCard.setBalance(creditCardRequest.getBalance());

        return ResponseEntity.ok(creditCardMapper.toDto(creditCardService.save(updatedCreditCard)));
    }

    @PostMapping
    public ResponseEntity<CreditCardDto> create(@RequestBody CreditCardRequest creditCardRequest) throws ResourceNotFoundException {
        Account requestedAccount = accountService.findAccountById(creditCardRequest.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + creditCardRequest.getAccountId()));

        CreditCard createdCreditCard = new CreditCard();
        createdCreditCard.setAccount(requestedAccount);
        createdCreditCard.setCardNumber(creditCardRequest.getCardNumber());
        createdCreditCard.setExpiredDate(creditCardRequest.getExpiredDate());
        createdCreditCard.setBalance(creditCardRequest.getBalance());

        creditCardService.save(createdCreditCard);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdCreditCard.getId()))
                .body(creditCardMapper.toDto(createdCreditCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        CreditCard deletedCreditCard = creditCardService.findCreditCardById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found " + id));
        creditCardService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}