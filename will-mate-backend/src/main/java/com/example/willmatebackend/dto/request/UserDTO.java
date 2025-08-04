package com.example.willmatebackend.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String email;
}
