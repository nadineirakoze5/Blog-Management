package org.example.blogmanagement.Repositories;
import org.example.blogmanagement.Models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositories extends MongoRepository<Post, String> {
    Page<Post> findAll(Pageable pageable);

}
