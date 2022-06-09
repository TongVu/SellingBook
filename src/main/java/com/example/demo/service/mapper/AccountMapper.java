package com.example.demo.service.mapper;

import com.example.demo.entity.Account;
import com.example.demo.service.dto.accountDto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;


//@Mapper
 @Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface AccountMapper {
//    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto toDto(Account account);
    List<AccountDto> toDtos(List<Account> accountList);
}