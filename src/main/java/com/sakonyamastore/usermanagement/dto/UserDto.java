package com.sakonyamastore.usermanagement.dto;


import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    @Id
    private Long id;

    @NotEmpty(message = "Firstname cannot be empty")
    @Size(min = 4, max = 8, message = "Firstname should be min 4 characters and max 8 characters")
    private String firstname;

    @NotEmpty(message = "Lastname cannot be empty")
    @Size(min = 4, max = 8, message = "Lastname should be min 4 characters and max 8 characters")
    private String lastname;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email is not valid",regexp = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[A-Za-z0-9][A-Za-z0-9\\+-]*(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotEmpty(message = "Phone number cannot be empty")
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
    @Pattern(regexp = "\\d{10}", message = "Phone number must contain only digits")
    private String phoneNumber;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, message = "Username should be min 4 characters")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(message = "Password is not valid", regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$")
    private String password;

    private Address address;
}
