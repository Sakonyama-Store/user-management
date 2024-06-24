package com.sakonyamastore.usermanagement.controller;

import com.sakonyamastore.usermanagement.dto.UserDto;
import com.sakonyamastore.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto user) throws Exception {
        Long userId = userService.createUser(user);
        String message = "User successfully created with userId : " + userId;
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable(value = "userId") Long userId) throws Exception {
        UserDto userDto = userService.getUser(userId);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "userId") Long userId) throws Exception {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
