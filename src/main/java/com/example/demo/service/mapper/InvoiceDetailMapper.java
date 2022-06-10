package com.example.demo.service.mapper;

import com.example.demo.entity.InvoiceDetail;
import com.example.demo.service.dto.invoiceDetailDto.InvoiceDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceDetailMapper {
    InvoiceDetailMapper INSTANCE = Mappers.getMapper(InvoiceDetailMapper.class);

    @Mapping(source = "invoice.invoiceDate", target = "invoiceInvoiceDate")
    @Mapping(source = "invoice.quantity", target = "invoiceQuantity")
    @Mapping(source = "invoice.totalPayment", target = "invoiceTotalPayment")
    @Mapping(source = "invoice.creditCard.cardNumber", target = "invoiceCreditCardCardNumber ")
    @Mapping(target = "invoiceAccountFullName",
            expression = "java(invoiceDetail.getInvoice().getAccount().getFirstName() + \" \" + invoiceDetail.getInvoice().getAccount().getLastName())")
    @Mapping(source = "ebook.title", target = "ebookTitle")

    InvoiceDetailDto toDto(InvoiceDetail invoiceDetail);
    List<InvoiceDetailDto> toDtos(List<InvoiceDetail> invoiceDetails);
}
