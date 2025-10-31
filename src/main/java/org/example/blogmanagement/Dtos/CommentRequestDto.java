package org.example.blogmanagement.Dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    @NotEmpty(message = "Post ID is required")
    private String postId;

    @NotEmpty(message = "Author ID is required")
    private String authorId;

    @NotEmpty(message = "Comment content cannot be blank")
    private String commentsContent;
}
