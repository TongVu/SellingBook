package com.example.demo.service.impl;

import com.example.demo.entity.CreditCard;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private final CreditCardRepository creditCardRepository;

    @Override
    public List<CreditCard> getAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public void save(CreditCard creditCard) {
        creditCardRepository.save(creditCard);

    }

    @Override
    public void deleteById(Integer id) {
        creditCardRepository.deleteById(id);

    }

    @Override
    public Optional<CreditCard> findCreditCardById(Integer id) {
        return creditCardRepository.findById(id);
    }
}
