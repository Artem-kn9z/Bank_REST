package com.example.bankcards.dto.user;

import com.example.bankcards.entity.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity of a user")
public class UserDTO {

    @NotEmpty(message = "Full name must be not empty")
    @Pattern(regexp = "^[А-ЯЁ][а-яё]+(?: [А-ЯЁ][а-яё]+){1,2}$", message = "Wrong fullName format")
    @Schema(description = "User full name", example = "Черняков Артём Сергеевич")
    private String fullName;

    @NotEmpty(message = "Username must be not empty")
    @Schema(description = "User's username", example = "chernjk")
    private String username;

    @NotEmpty(message = "Email must be not empty")
    @Schema(description = "User's email", example = "pupok1015@mail.ru")
    private String email;

    @NotEmpty(message = "Password must be not empty")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 symbols")
    @Schema(description = "password", example = "12345")
    private String password;

    @NotNull(message = "Role must be not empty")
    @Schema(description = "User's role", example = "USER")
    private Role role;
}
