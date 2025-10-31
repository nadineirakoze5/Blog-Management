package org.example.blogmanagement.Controller;

import jakarta.validation.Valid;
import org.example.blogmanagement.Dtos.UserRequestDto;
import org.example.blogmanagement.Dtos.UserResponseDto;
import org.example.blogmanagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> InsertUser(@Valid @RequestBody UserRequestDto userRequest) {

        UserResponseDto user = userService.InsertUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @GetMapping
    public List<UserResponseDto> getAllUser()  {
        return userService.getAllUser();
    }


    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}