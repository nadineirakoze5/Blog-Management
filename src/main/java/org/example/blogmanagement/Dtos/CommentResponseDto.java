package org.example.blogmanagement.Dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private String commentId;
    private String postId;
    private String authorId;
    private String commentsContent;
}
