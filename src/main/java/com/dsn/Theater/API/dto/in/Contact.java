package com.dsn.Theater.API.dto.in;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Valid
public class Contact {
    //@Size(min = 2, max = 100)
    @NotBlank
    private String firstName;

    @Size(min = 2, max = 100)
    @NotBlank
    private String lastName;

    @Email
    private String email;

    @Size(min = 7, max = 16)
    @NotBlank
    private String phone;
}
