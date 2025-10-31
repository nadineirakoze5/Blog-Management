package org.example.blogmanagement.Service;
import org.example.blogmanagement.Dtos.UserRequestDto;
import org.example.blogmanagement.Dtos.UserResponseDto;
import org.example.blogmanagement.Models.User;
import org.example.blogmanagement.Repositories.UserRepositories;
import org.example.blogmanagement.GlobalException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final List<UserResponseDto> users = new ArrayList<>();
    private UserRepositories Repository;

    @Autowired
    public UserService(UserRepositories Repository) {
        this.Repository = Repository;
    }
    public UserResponseDto InsertUser(UserRequestDto UserRequest) {
        User user = new User();

        user.setUserName(UserRequest.getUsername());
        user.setUserEmail(UserRequest.getEmail());
        user.setPassword(UserRequest.getPassword());

        Repository.save(user);
        return new UserResponseDto(user.getUserId(), user.getUserName(), user.getUserEmail());
    }

    public List<UserResponseDto> getAllUser() {
        return Repository.findAll().stream().map(User -> new UserResponseDto(User.getUserId(), User.getUserName(), User.getUserEmail()))
                .collect(Collectors.toList());

    }


    public UserResponseDto getUserById(String UserId) {
        User user = Repository.findById(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + UserId));
        return new UserResponseDto(user.getUserId(), user.getUserName(), user.getUserEmail());
    }


    public void deleteUser(String UserId) {
        if (!Repository.existsById(UserId)) {
            throw new ResourceNotFoundException(" We can't delete User with this id: " + UserId);
        }
      Repository.deleteById(UserId);
    }
}
