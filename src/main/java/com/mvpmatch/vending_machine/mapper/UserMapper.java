package com.mvpmatch.vending_machine.mapper;

import com.mvpmatch.vending_machine.dto.UserDto;
import com.mvpmatch.vending_machine.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToEntity(UserDto userModel);

    UserDto entityToDto(User user);
}
