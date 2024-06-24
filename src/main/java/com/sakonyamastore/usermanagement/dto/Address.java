package com.sakonyamastore.usermanagement.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Address {
    @NotEmpty
    private String addressLine1;

    private String addressLine2;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @NotEmpty
    private String country;

    @NotEmpty
    private String postalCode;
}
