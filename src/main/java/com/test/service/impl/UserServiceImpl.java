package com.test.service.impl;

import com.test.dto.RequestDto;
import com.test.dto.ResponseDto;
import com.test.dto.UserDto;
import com.test.exceptions.InvalidStatusException;
import com.test.exceptions.NonUniqueEmailException;
import com.test.exceptions.UserNotFoundException;
import com.test.mapper.UserMapper;
import com.test.model.repository.UserRepository;
import com.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public List<UserDto> findAll() {
        return userMapper.listToDto(userRepository.findAll());
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        UserDto user = userMapper.toDto(userRepository.findByEmail(userDto.getEmail()));
        if (user == null) {
            return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
        } else {
            throw new NonUniqueEmailException("Email is already taken. Try again.");
        }
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User with id " + id + " is not found.")));
    }

    public ResponseDto findUserStatus(RequestDto requestDto, Long id) {
        ResponseDto responseDto = new ResponseDto();
        String status = requestDto.getStatus();
        UserDto currentUser = findById(id);
        responseDto.setId(currentUser.getId());
        responseDto.setOldStatus(currentUser.getStatus());
        if ((status).equals("online") | (status).equals("offline")) {
            currentUser.setStatus(status);
            saveUser(currentUser);
            responseDto.setNewStatus(currentUser.getStatus());
        } else {
            throw new InvalidStatusException("Status is incorrect.");
        }
        return responseDto;
    }
}
