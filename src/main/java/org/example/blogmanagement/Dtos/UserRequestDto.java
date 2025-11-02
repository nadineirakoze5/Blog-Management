package org.example.blogmanagement.Dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotEmpty(message = "Username cannot be blank")
    private String username;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email cannot be blank")
    private String email;

    @NotNull(message = "Password cannot be blank")
    private int password;

}
