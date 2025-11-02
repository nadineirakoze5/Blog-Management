package org.example.blogmanagement.Controller;
import jakarta.validation.Valid;
import org.example.blogmanagement.Dtos.CommentRequestDto;
import org.example.blogmanagement.Dtos.CommentResponseDto;
import org.example.blogmanagement.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentservice;

    @Autowired
    public CommentController(CommentService commentservice) {
        this.commentservice = commentservice;
    }

    @PostMapping
    public CommentResponseDto createComment(@Valid @RequestBody CommentRequestDto request) {
        return commentservice.createComment(request);
    }

    @GetMapping
    public List<CommentResponseDto> getAllComments() {
        return commentservice.getAllComments();
    }

    @GetMapping("/{id}")
    public CommentResponseDto getCommentById(@PathVariable String id) {
        return commentservice.getCommentById(id);
    }

//    @GetMapping("/post/{postId}")
//    public List<CommentResponseDto> getCommentsByPostId(@PathVariable String postId) {
//        return commentservice.getCommentsByPostId(postId);
//    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable String id) {
        commentservice.deleteComment(id);
        return "Comment deleted successfully";
    }


    @GetMapping("/page")
    public Page<CommentResponseDto> getComments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "commentContent") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return commentservice.getCommentsPaginated(page, size, sortBy, sortDir);
    }
}
