package ru.kolpakovee.authorization_microservice.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest implements AuthRequest{
    @NotBlank(message = "Username cannot be empty.")
    private String username;
    @Email(message = "Incorrect email address.")
    private String email;
    @NotBlank(message = "Password cannot be empty.")
    private String password;
    @NotBlank(message = "Role cannot be empty.")
    private String role;
}
