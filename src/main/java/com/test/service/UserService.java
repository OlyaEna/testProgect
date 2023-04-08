package com.test.service;

import com.test.dto.RequestDto;
import com.test.dto.ResponseDto;
import com.test.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto addUser(UserDto userDto);
    UserDto findById(Long id);
    ResponseDto findUserStatus(RequestDto requestDto, Long id);

}
