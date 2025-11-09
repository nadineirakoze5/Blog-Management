package org.example.blogmanagement.Repositories;
import org.example.blogmanagement.Models.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepositories extends MongoRepository<Comments, String> {
void deleteByPostId(String postId);
}
