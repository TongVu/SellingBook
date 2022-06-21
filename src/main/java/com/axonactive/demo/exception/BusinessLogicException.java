package com.axonactive.demo.exception;

import org.springframework.http.HttpStatus;

public class BusinessLogicException {
    public static ResponseException notFound(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.NOT_FOUND);
    }

    public static ResponseException badRequest(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseException internalServerError(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*  BOILER PLATE
        private static final String EXCEPTION_NOT_FOUND_KEY = "ExceptionKey";
        private static final String EXCEPTION_NOT_FOUND_MSG = "Exception msg";
     */

    private static final String ACCOUNT_NOT_FOUND_KEY = "AccountNotFound";
    private static final String ACCOUNT_NOT_FOUND_MSG = "Account not found";

    public static ResponseException accountNotFound() {
        return notFound(ACCOUNT_NOT_FOUND_KEY, ACCOUNT_NOT_FOUND_MSG);
    }

    private static final String AUTHOR_NOT_FOUND_KEY = "AuthorNotFound";
    private static final String AUTHOR_NOT_FOUND_MSG = "Author Not Found";

    public static ResponseException authorNotFound() {
        return notFound(AUTHOR_NOT_FOUND_KEY, AUTHOR_NOT_FOUND_MSG);
    }

    private static final String CATEGORY_EBOOK_RELATION_NOT_FOUND_KEY = "CategoryEbookRelationNotFound";
    private static final String CATEGORY_EBOOK_RELATION_NOT_FOUND_MSG = "Category Ebook Relation Not Found";

    public static ResponseException categoryEbookRelationNotFound() {
        return notFound(CATEGORY_EBOOK_RELATION_NOT_FOUND_KEY, CATEGORY_EBOOK_RELATION_NOT_FOUND_MSG);
    }

    private static final String CATEGORY_NOT_FOUND_KEY = "CategoryNotFound";
    private static final String CATEGORY_NOT_FOUND_MSG = "Category Not Found";

    public static ResponseException categoryNotFound() {
        return notFound(CATEGORY_NOT_FOUND_KEY, CATEGORY_NOT_FOUND_MSG);
    }

    private static final String COMMENT_EBOOK_RELATION_NOT_FOUND_KEY = "CommentEbookRelationNotFound";
    private static final String COMMENT_EBOOK_RELATION_NOT_FOUND_MSG = "Comment Ebook Relation Not Found";

    public static ResponseException commenEbookRelationNotFound() {
        return notFound(COMMENT_EBOOK_RELATION_NOT_FOUND_KEY, COMMENT_EBOOK_RELATION_NOT_FOUND_MSG);
    }

    private static final String COMMENT_NOT_FOUND_KEY = "CommentNotFound";
    private static final String COMMENT_NOT_FOUND_MSG = "Comment Not Found";

    public static ResponseException commenNotFound() {
        return notFound(COMMENT_NOT_FOUND_KEY, COMMENT_NOT_FOUND_MSG);
    }

    private static final String CREDIT_CARD_NOT_FOUND_KEY = "CreditCardNotFound";
    private static final String CREDIT_CARD_NOT_FOUND_MSG = "Credit Card Not Found";

    public static ResponseException creditCardNotFound() {
        return notFound(CREDIT_CARD_NOT_FOUND_KEY, CREDIT_CARD_NOT_FOUND_MSG);
    }

    private static final String EBOOK_AUTHOR_RELATION_NOT_FOUND_KEY = "EbookAuthorRelationNotFound";
    private static final String EBOOK_AUTHOR_RELATION_NOT_FOUND_MSG = "Ebook Author Relation Not Found";

    public static ResponseException ebookAuthorRelationNotFound() {
        return notFound(EBOOK_AUTHOR_RELATION_NOT_FOUND_KEY, EBOOK_AUTHOR_RELATION_NOT_FOUND_MSG);
    }

    private static final String EBOOK_NOT_FOUND_KEY = "EbookNotFound";
    private static final String EBOOK_NOT_FOUND_MSG = "Ebook Not Found";

    public static ResponseException ebookNotFound() {
        return notFound(EBOOK_NOT_FOUND_KEY, EBOOK_NOT_FOUND_MSG);
    }

    private static final String INVOICE_DETAIL_NOT_FOUND_KEY = "InvoiceDetailNotFound";
    private static final String INVOICE_DETAIL_NOT_FOUND_MSG = "Invoice Detail Not Found";

    public static ResponseException invoiceDetailNotFound() {
        return notFound(INVOICE_DETAIL_NOT_FOUND_KEY, INVOICE_DETAIL_NOT_FOUND_MSG);
    }

    private static final String INVOICE_NOT_FOUND_KEY = "InvoiceNotFound";
    private static final String INVOICE_NOT_FOUND_MSG = "Invoice Not Found";

    public static ResponseException invoiceNotFound() {
        return notFound(INVOICE_NOT_FOUND_KEY, INVOICE_NOT_FOUND_MSG);
    }

    private static final String PUBLISHER_NOT_FOUND_KEY = "PublisherNotFound";
    private static final String PUBLISHER_NOT_FOUND_MSG = "Publisher Not Found";

    public static ResponseException publisherNotFound() {
        return notFound(PUBLISHER_NOT_FOUND_KEY, PUBLISHER_NOT_FOUND_MSG);
    }

    private static final String ACCOUNT_AND_CREDIT_CARD_NOT_MATCH_KEY = "AccountAndCreditCardNotMatch";
    private static final String ACCOUNT_AND_CREDIT_CARD_NOT_MATCH_MSG = "Account And Credit Card Not Match";

    public static ResponseException accountAndCreditCardNotMatch() {
        return notFound(ACCOUNT_AND_CREDIT_CARD_NOT_MATCH_KEY, ACCOUNT_AND_CREDIT_CARD_NOT_MATCH_MSG);
    }
}
