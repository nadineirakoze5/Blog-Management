package org.example.blogmanagement.Dtos;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    @NotEmpty(message = "Post title cannot be blank")
    private String postTitle;

    @NotEmpty(message = "Post content cannot be blank")
    private String postContent;

    @NotEmpty(message = "User ID is required")
    @Min(value = 1, message = "User ID must be greater than 0")
    private int authorId;
}
