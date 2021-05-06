package com.pibox.knwh.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pibox.knwh.entity.Company;
import com.pibox.knwh.enumeration.Gender;
import com.pibox.knwh.enumeration.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class UserDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]{1,20}$", message = "Only up to 20 letters allowed")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z]{1,20}$", message = "Only up to 20 letters allowed")
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Birth date is required")
    private Date birthDate;

    @NotBlank(message = "Company name is required")
    @Pattern(regexp = "^[0-9]{6,10}$", message = "Only 6 to 10 numbers allowed")
    private String phoneNumber;

    @Email(message = "Please enter valid Email")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Please use only allowed role")
    private Role role;

    @NotNull(message = "Please use only allowed gender")
    private Gender gender;

    @Pattern(regexp = "^[0-9]{1,10}$", message = "Only numbers allowed")
    private String companyId;
}
