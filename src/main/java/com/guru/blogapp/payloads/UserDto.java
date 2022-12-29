package com.guru.blogapp.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private long id;

    @NotEmpty
    @Size(min = 4, message = "name must be min of 4 characters!")
    private String name;

    @Email(message = "email address is not valid!")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be min of 3 chars and max 10 chars!")
    private String password;

    @NotEmpty
    private String about;
}
