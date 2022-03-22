package com.online_dtie_tracker.Dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;

    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotEmpty(message = "address must not be empty")
    private String address;

    @NotEmpty(message = "Mobile number must not be empty")
    @Pattern(regexp = "^\\+?(?:977)?[ -]?(?:(?:(?:98|97)-?\\d{8})|(?:01-?\\d{7}))$",
            message = "Invalid number.")
    @Size(max = 10,min = 10 ,message = "Not valid length of number")
    private String contact;

    @NotEmpty(message = "email must not be empty")
    @Email(message = "enter valid email")
    private String email;

    @NotEmpty(message = "password must not be empty")
    private String password;

    @NotEmpty(message = "password must not be empty")
    private String reTypePassword;
}
