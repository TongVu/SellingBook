package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.CreditCardRequest;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.entity.CreditCard;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.repository.CreditCardRepository;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public List<CreditCard> getAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);

    }

    @Override
    public CreditCard update(CreditCard updatedCreditCard, CreditCardRequest creditCardRequest) {
        log.info("Searching for account has id {} ", creditCardRequest.getAccountId());
        Account requestedAccount = accountService.findAccountById(creditCardRequest.getAccountId())
                .orElseThrow(BusinessLogicException::authorNotFound);

        updatedCreditCard.setAccount(requestedAccount);
        updatedCreditCard.setCardNumber(creditCardRequest.getCardNumber());
        updatedCreditCard.setExpiredDate(creditCardRequest.getExpiredDate());
        updatedCreditCard.setBalance(creditCardRequest.getBalance());

        return creditCardRepository.save(updatedCreditCard);
    }

    @Override
    public CreditCard create(CreditCardRequest creditCardRequest) {
        log.info("Searching for account has id {} ", creditCardRequest.getAccountId());
        Account requestedAccount = accountService.findAccountById(creditCardRequest.getAccountId())
                .orElseThrow(BusinessLogicException::accountNotFound);

        CreditCard createdCreditCard = new CreditCard();
        createdCreditCard.setAccount(requestedAccount);
        createdCreditCard.setCardNumber(creditCardRequest.getCardNumber());
        createdCreditCard.setExpiredDate(creditCardRequest.getExpiredDate());
        createdCreditCard.setBalance(creditCardRequest.getBalance());

        return creditCardRepository.save(createdCreditCard);
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
