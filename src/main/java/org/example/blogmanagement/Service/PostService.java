package org.example.blogmanagement.Service;
import org.example.blogmanagement.Dtos.PostRequestDto;
import org.example.blogmanagement.Dtos.PostResponseDto;
import org.example.blogmanagement.GlobalException.ResourceNotFoundException;
import org.example.blogmanagement.Models.Post;
import org.example.blogmanagement.Models.User;
import org.example.blogmanagement.Repositories.CommentsRepositories;
import org.example.blogmanagement.Repositories.PostRepositories;
import org.example.blogmanagement.Repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepositories Repository;
    @Autowired
    private  CommentsRepositories commentsRepository;

    @Autowired
    private  UserRepositories UserRepositories;

    @Autowired
    public PostService(PostRepositories Repository) {

        this.Repository = Repository;
    }


    public PostResponseDto CreatePost(PostRequestDto request, User currentUser) {
        User user = UserRepositories.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("User with Id '" + request.getAuthorId() + "' was not found"));


        Post post = new Post();
        post.setPostTitle(request.getPostTitle());
        post.setPostContent(request.getPostContent());
        post.setAuthorId(user.getUserId());

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


    public void deletePost(String id, User currentUser) {
        Post post = Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("We can't delete User with this id: " + id));

        if (post.getAuthorId() != currentUser.getUserId() && !"ADMIN".equals(currentUser.getRole())) {
            throw new RuntimeException("You are not allowed to delete this post");
        }


        commentsRepository.deleteByPostId(post.getPostId());

        Repository.delete(post);
    }


    public Page<PostResponseDto> getPostsPaginated(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return Repository.findAll(pageable)
                .map(post -> new PostResponseDto(post.getPostId(), post.getPostTitle(), post.getPostContent(), post.getAuthorId()));
    }
}

