package com.miratextile.clothingmanagement.dto.response;

public class AuthResponseDto {
    private String token;

    public AuthResponseDto(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}