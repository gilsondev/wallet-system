package com.wallet.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
    private Long id;

    @Length(min = 3, max = 50, message = "Name should have between 3 and 50 characters")
    private String name;

    @Email(message = "E-mail is invalid")
    private String email;

    @NotNull
    @Length(min = 6, max = 50, message = "Password should have minimal of 6 characters")
    private String password;
}
