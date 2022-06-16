package com.axonactive.demo.service.mapper;

import com.axonactive.demo.entity.CreditCard;
import com.axonactive.demo.service.dto.creditCardDto.CreditCardDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

//    @Mapping(target = "accountFullName", expression = "java(creditCard.getAccount().getFirstName() + \" \" + creditCard.getAccount().getLastName())")
    @Mapping(source = "account.dob", target = "accountDob")
    @Mapping(source = "account.gender", target = "accountGender")
    @Mapping(source = "account.email", target = "accountEmail")
    @Mapping(source = "account.phone", target = "accountPhone")

    CreditCardDto toDto(CreditCard creditCard);
    List<CreditCardDto> toDtos(List<CreditCard> creditCards);

    @AfterMapping
    default void setAccountFullName(CreditCard creditCard, @MappingTarget CreditCardDto target) {
        target.setAccountFullName(creditCard.getAccount().getFirstName()+ " " +creditCard.getAccount().getLastName());
    }
}
