package com.axonactive.demo.service;

import com.axonactive.demo.entity.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {
    List<CreditCard> getAll();

    CreditCard save(CreditCard creditCard);

    void deleteById(Integer id);

    Optional<CreditCard> findCreditCardById(Integer id);
}
