package com.ntconsult.testentconsult.models;

import lombok.Data;

@Data
public class LoginResposta {
    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String image;
    private String token;
    private String refreshToken;    
}
