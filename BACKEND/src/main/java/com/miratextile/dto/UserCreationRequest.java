package com.miratextile.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserCreationRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Role is required")
    @Pattern(regexp = "^(SALES_STAFF|INVENTORY_STAFF)$", 
            message = "Role must be either SALES_STAFF or INVENTORY_STAFF")
    private String role;
}