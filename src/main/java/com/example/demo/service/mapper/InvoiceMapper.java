package com.example.demo.service.mapper;

import com.example.demo.entity.Invoice;
import com.example.demo.service.dto.invoiceDto.InvoiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(source = "creditCard.cardNumber", target = "creditCardCardNumber")
    @Mapping(target = "accountFullName", expression = "java(invoice.getAccount().getFirstName() + \" \" + invoice.getAccount().getLastName())")

    InvoiceDto toDto(Invoice invoice);
    List<InvoiceDto> toDtos(List<Invoice> invoiceList);
}
