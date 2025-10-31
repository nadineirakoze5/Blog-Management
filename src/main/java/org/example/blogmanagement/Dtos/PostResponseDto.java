package org.example.blogmanagement.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private String postId;
    private String postTitle;
    private String postContent;
    private int authorId;
}
