package org.example.blogmanagement.Models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Comment")
public class Comments {
    @Id
    private String commentId;
    private String postId;
    private String authorId;
    private String commentsContent;
}
