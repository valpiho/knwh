package com.pibox.knwh.entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CompanyDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Company name is required")
    private String title;

    @NotBlank(message = "Country name is required")
    private String country;

    @NotBlank(message = "City name is required")
    private String city;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Zip Code is required")
    @Size(min = 5, max = 8, message = "Please use 5 to 8 characters")
    private String zipCode;

    @NotBlank(message = "Phone number is required")
    @Size(min = 8, max = 15, message = "Please use 8 to 15 numbers")
    private String phoneNumber;

    @NotBlank(message = "VAT number is required")
    @Size(min = 8, max = 15, message = "Please use 8 to 15 characters")
    private String vatNumber;

    @Email(message = "Please enter valid Email")
    @NotEmpty(message = "Email is required")
    private String email;
}
