package com.itzcorpio.spring_em_sys.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
