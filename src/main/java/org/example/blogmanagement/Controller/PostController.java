package org.example.blogmanagement.Controller;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.example.blogmanagement.Dtos.PostRequestDto;
import org.example.blogmanagement.Dtos.PostResponseDto;
import org.example.blogmanagement.Models.User;
import org.example.blogmanagement.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponseDto CreatePost(@Valid @RequestBody PostRequestDto request, Authentication auth) {
        User currentUser = (User) auth.getPrincipal();
        return postService.CreatePost(request, currentUser);
    }

    @GetMapping
    public List<PostResponseDto> getAllPosts() {

        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostResponseDto getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id, Authentication auth) {
        User currentUser = (User) auth.getPrincipal();
        postService.deletePost(id, currentUser);
        return ResponseEntity.ok( "Post deleted successfully");
    }



    @GetMapping("/page")
    public ResponseEntity<Page<PostResponseDto>> getAllPostsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "postId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Page<PostResponseDto> posts = postService.getPostsPaginated(page, size, sortBy, sortDir);
        return ResponseEntity.ok(posts);
    }


}

