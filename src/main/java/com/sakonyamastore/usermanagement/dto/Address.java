package com.sakonyamastore.usermanagement.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

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
