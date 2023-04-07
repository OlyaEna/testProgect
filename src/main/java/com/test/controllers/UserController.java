package com.test.controllers;

import com.test.dto.RequestDto;
import com.test.dto.ResponseDto;
import com.test.dto.UserDto;
import com.test.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    /**
     Добавление нового пользователя
     */
    @PostMapping("/add")
    public ResponseEntity<Long> addUser(@Valid @RequestBody UserDto userDto) {
        UserDto user = userService.saveUser(userDto);
        return new ResponseEntity<>(user.getId(), HttpStatus.CREATED);
    }

    /**
     Получение всех пользователей
     */
    @GetMapping(path = "/users")
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }


    /**
    Получение информации о пользователе по id
     */
    @GetMapping(path = "/user/{id}")
    public UserDto findUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     Изменение статуса пользователя, получение id и прошлого статуса
     */
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ResponseDto> updateUser( @PathVariable("id") long id,
                                                   @Valid @RequestBody RequestDto requestDto) {
        ResponseDto user = userService.findUserStatus(requestDto, id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}