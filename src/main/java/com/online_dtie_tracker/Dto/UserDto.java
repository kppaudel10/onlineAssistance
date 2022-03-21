package com.online_dtie_tracker.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String password;
}
