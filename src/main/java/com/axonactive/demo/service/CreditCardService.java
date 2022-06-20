package com.axonactive.demo.service;

import com.axonactive.demo.controller.request.CreditCardRequest;
import com.axonactive.demo.entity.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {
    List<CreditCard> getAll();

    CreditCard save(CreditCard creditCard);

    CreditCard update(CreditCard updatedCreditCard, CreditCardRequest creditCardRequest);

    CreditCard create(CreditCardRequest creditCardRequest);

    void deleteById(Integer id);

    Optional<CreditCard> findCreditCardById(Integer id);
}
