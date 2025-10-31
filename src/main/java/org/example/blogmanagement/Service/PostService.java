package org.example.blogmanagement.Service;
import org.example.blogmanagement.Dtos.PostRequestDto;
import org.example.blogmanagement.Dtos.PostResponseDto;
import org.example.blogmanagement.GlobalException.ResourceNotFoundException;
import org.example.blogmanagement.Models.Post;
import org.example.blogmanagement.Models.User;
import org.example.blogmanagement.Repositories.PostRepositories;
import org.example.blogmanagement.Repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepositories Repository;

//    @Autowired
//    private  UserRepositories UserRepositories;

    @Autowired
    public PostService(PostRepositories Repository) {
        this.Repository = Repository;
    }


    public PostResponseDto CreatePost(PostRequestDto request) {
        User user = new User();
                
        Post post = new Post();
        post.setPostTitle(request.getPostTitle());
        post.setPostContent(request.getPostContent());
        post.setAuthorId(request.getAuthorId());

        Repository.save(post);

        return new PostResponseDto(post.getPostId(), post.getPostTitle(), post.getPostContent(), post.getAuthorId());
    }

    public List<PostResponseDto> getAllPosts() {
        return Repository.findAll().stream()
                .map(post -> new PostResponseDto(post.getPostId(), post.getPostTitle(), post.getPostContent(), post.getAuthorId()))
                .collect(Collectors.toList());
    }

    public PostResponseDto getPostById(String id) {
        Post post = Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        return new PostResponseDto(post.getPostId(), post.getPostTitle(), post.getPostContent(), post.getAuthorId());
    }


    public void deletePost(String id) {
        Post post = Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("We can't delete User with this id: " + id));
        Repository.delete(post);
    }
}

