package org.example.blogmanagement.Service;
import org.example.blogmanagement.Dtos.CommentRequestDto;
import org.example.blogmanagement.Dtos.CommentResponseDto;
import org.example.blogmanagement.GlobalException.ResourceNotFoundException;
import org.example.blogmanagement.Models.Comments;
import org.example.blogmanagement.Repositories.CommentsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentsRepositories commentRepository;

    @Autowired
    public CommentService(CommentsRepositories commentRepository) {
        this.commentRepository = commentRepository;
    }


    public CommentResponseDto createComment(CommentRequestDto request) {
        Comments comment = new Comments();
        comment.setPostId(request.getPostId());
        comment.setAuthorId(request.getAuthorId());
        comment.setCommentsContent(request.getCommentsContent());

        commentRepository.save(comment);
        return new CommentResponseDto(comment.getCommentId(), comment.getPostId(), comment.getAuthorId(), comment.getCommentsContent());
    }


    public List<CommentResponseDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(comment -> new CommentResponseDto(comment.getCommentId(), comment.getPostId(), comment.getAuthorId(), comment.getCommentsContent()))
                .collect(Collectors.toList());
    }


    public CommentResponseDto getCommentById(String id) {
        Comments comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));

        return new CommentResponseDto(comment.getCommentId(), comment.getPostId(), comment.getAuthorId(), comment.getCommentsContent());
    }


//    public List<CommentResponseDto> getCommentsByPostId(String postId) {
//        return commentRepository.findByPostId(postId).stream()
//                .map(comment -> new CommentResponseDto(comment.getCommentId(), comment.getPostId(), comment.getUserId(), comment.getContent()))
//                .collect(Collectors.toList());
//    }


    public void deleteComment(String id) {
        Comments comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));
        commentRepository.delete(comment);
    }
}
