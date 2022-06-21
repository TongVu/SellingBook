package com.axonactive.demo.repository;

import com.axonactive.demo.entity.Account;
import com.axonactive.demo.service.dto.accountDto.AccountInvoicesDto;
import com.axonactive.demo.service.dto.ebookDto.EbookPurchasedDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findAccountByEmailContaining(String email);

    Optional<Account> findAccountByPhone(String phone);

    @Query(
            "SELECT new com.axonactive.demo.service.dto.ebookDto.EbookPurchasedDto(e.title, e.rating, e.introduction, in_de.ebookPrice, e.viewLinkStatus) " +
                    "FROM Account a, Invoice i, InvoiceDetail in_de, Ebook e " +
                    "WHERE a.id = i.account.id AND " +
                    "i.id = in_de.invoice.id AND " +
                    "in_de.ebook.id = e.id AND " +
                    "i.isPay = TRUE AND a.id = ?1")
    List<EbookPurchasedDto> findPurchasedEbooks(Integer id);

    @Query(
            "SELECT new com.axonactive.demo.service.dto.ebookDto.EbookPurchasedDto(e.title, e.rating, e.introduction, in_de.ebookPrice, e.viewLinkStatus) " +
                    "FROM Account a, Invoice i, InvoiceDetail in_de, Ebook e " +
                    "WHERE a.id = i.account.id AND " +
                    "i.id = in_de.invoice.id AND " +
                    "in_de.ebook.id = e.id AND " +
                    "i.isPay = FALSE AND a.id = ?1")
    List<EbookPurchasedDto> findNotPurchasedEbooks(Integer id);

    @Query("SELECT new com.axonactive.demo.service.dto.accountDto.AccountInvoicesDto(i.invoiceDate, i.quantity, i.isPay, i.totalPayment, in_de.dateAdded, in_de.ebookPrice) " +
            "FROM Account a, Invoice i, InvoiceDetail in_de " +
            "WHERE a.id = i.account.id AND " +
            "i.id = in_de.invoice.id AND " +
            "a.id = ?1 " +
            "ORDER BY in_de.dateAdded ")
    List<AccountInvoicesDto> findAllInvoices(Integer id);
}
