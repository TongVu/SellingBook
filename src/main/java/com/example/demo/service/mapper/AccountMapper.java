package com.example.demo.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = AccountMapper.getMapping(AccountMapper.class);
}
