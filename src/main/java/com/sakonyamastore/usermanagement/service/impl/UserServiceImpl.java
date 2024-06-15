package com.sakonyamastore.usermanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakonyamastore.usermanagement.Exception.CustomCheckedException;
import com.sakonyamastore.usermanagement.config.UserPasswordEncoder;
import com.sakonyamastore.usermanagement.dto.UserDto;
import com.sakonyamastore.usermanagement.entity.UserEntity;
import com.sakonyamastore.usermanagement.repository.UserRepository;
import com.sakonyamastore.usermanagement.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserPasswordEncoder userPasswordEncoder;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Long createUser(UserDto requestDto) throws Exception {
        Optional<UserEntity> userFound = userRepository.findByEmailOrUserName(
                encodeUserDetails(requestDto.getEmail()), encodeUserDetails(requestDto.getUsername()));

        if (userFound.isPresent()){
            throw new CustomCheckedException("User already exist with given username or email");
        }

        return userRepository.save(mapUserEntity(requestDto)).getId();
    }

    @Override
    public UserDto getUser(Long userId) throws Exception {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new CustomCheckedException("User not found"));
        return mapUserDto(user);
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        userRepository.findById(userId).orElseThrow(() -> new CustomCheckedException("User not found"));
        userRepository.deleteById(userId);
    }

    private UserEntity mapUserEntity(UserDto userRequestDto) throws JsonProcessingException {
        UserEntity user = new UserEntity();
        user.setFirstname(userRequestDto.getFirstname());
        user.setLastname(userRequestDto.getLastname());
        user.setEmail(encodeUserDetails(userRequestDto.getEmail()));
        user.setPhoneNumber(encodeUserDetails(userRequestDto.getPhoneNumber()));
        user.setUsername(encodeUserDetails(userRequestDto.getUsername()));
        user.setPassword(userPasswordEncoder.passwordEncoder().encode(userRequestDto.getPassword()));
//        user.setAddress(objectMapper.writeValueAsString(userRequestDto.getAddress()));
        return user;
    }

    private UserDto mapUserDto(UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setFirstname(userEntity.getFirstname());
        userDto.setLastname(userEntity.getLastname());
        userDto.setEmail(decodeUserDetails(userEntity.getEmail()));
        userDto.setPhoneNumber(decodeUserDetails(userEntity.getPhoneNumber()));
        userDto.setUsername(decodeUserDetails(userEntity.getUsername()));
        userDto.setPassword("********");
        return userDto;
    }

    private String encodeUserDetails(String parameter){
        return Base64.getEncoder().encodeToString(parameter.getBytes(StandardCharsets.UTF_8));
    }

    private String decodeUserDetails(String parameter){
        return new String(Base64.getDecoder().decode(parameter), StandardCharsets.UTF_8);
    }
}
