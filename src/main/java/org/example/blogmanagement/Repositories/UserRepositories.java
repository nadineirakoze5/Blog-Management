package org.example.blogmanagement.Repositories;
import org.example.blogmanagement.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositories extends JpaRepository<User, String> {
    Page<User> findAll(Pageable pageable);

    User findByUserEmail(String userEmail);
}
