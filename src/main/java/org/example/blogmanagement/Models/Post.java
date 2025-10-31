package org.example.blogmanagement.Models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Post")
public class Post {
    @Id
    private String postId;
    private String postTitle;
    private String postContent;
    private int authorId;
}
