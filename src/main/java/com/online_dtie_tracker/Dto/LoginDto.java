package com.online_dtie_tracker.Dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    @NotEmpty(message = "userName required")
    private String userName;

    @NotEmpty(message = "password required")
    private String password;
}
