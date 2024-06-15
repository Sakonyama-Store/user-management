package com.sakonyamastore.usermanagement.service;

import com.sakonyamastore.usermanagement.Exception.CustomCheckedException;
import com.sakonyamastore.usermanagement.dto.UserDto;

public interface UserService {

    Long createUser(UserDto user) throws Exception;

    UserDto getUser(Long userId) throws Exception;

    void deleteUser(Long userId) throws Exception;
}
