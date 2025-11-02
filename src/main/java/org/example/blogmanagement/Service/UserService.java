package org.example.blogmanagement.Service;
import lombok.extern.slf4j.Slf4j;
import org.example.blogmanagement.Dtos.UserRequestDto;
import org.example.blogmanagement.Dtos.UserResponseDto;
import org.example.blogmanagement.Models.User;
import org.example.blogmanagement.Repositories.UserRepositories;
import org.example.blogmanagement.GlobalException.ResourceNotFoundException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final List<UserResponseDto> users = new ArrayList<>();
    private final UserRepositories Repository;

    @Autowired
    public UserService(UserRepositories Repository) {

        this.Repository = Repository;
    }
    public UserResponseDto InsertUser(UserRequestDto UserRequest) {

//        log.info("Added user to database");

        User user = new User();

        user.setUserName(UserRequest.getUsername());
        user.setUserEmail(UserRequest.getEmail());
        user.setPassword(UserRequest.getPassword());

        Repository.save(user);
//        logger.info("Added user to database");
        return new UserResponseDto(user.getUserId(), user.getUserName(), user.getUserEmail());
    }

    public List<UserResponseDto> getAllUser() {
//        log.info("Fetching all users");
        return Repository.findAll().stream().map(User -> new UserResponseDto(User.getUserId(), User.getUserName(), User.getUserEmail()))
                .collect(Collectors.toList());

    }


    public UserResponseDto getUserById(String UserId) {
        User user = Repository.findById(UserId)
                .orElseThrow(() -> {
                    log.warn("User not found with id {}", UserId);
                    return new ResourceNotFoundException("User not found with id: " + UserId);
                });
        log.info("Fetched user {} with id {}", user.getUserName(), UserId);
        return new UserResponseDto(user.getUserId(), user.getUserName(), user.getUserEmail());
    }



    public void deleteUser(String UserId) {
        if (!Repository.existsById(UserId)) {
            throw new ResourceNotFoundException(" We can't delete User with this id: " + UserId);
        }
      Repository.deleteById(UserId);
    }


    public Page<UserResponseDto> getUsersPaginated(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return Repository.findAll(pageable)
                .map(user -> new UserResponseDto(user.getUserId(), user.getUserName(), user.getUserEmail()));
    }
}
