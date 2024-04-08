package com.social.socialapplication.controller;

import com.social.socialapplication.dto.request.UserRequest;
import com.social.socialapplication.dto.response.UserResponse;
import com.social.socialapplication.entity.User;
import com.social.socialapplication.repository.UserRepository;
import com.social.socialapplication.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
  @GetMapping("/users")
  public List<UserResponse>getAllUser(){
      return userService.getAllUser();

      }

    @GetMapping("/{username}")
    public UserResponse getOneUserByUsername(@PathVariable String username){
        return userService.getOneUserByUsername(username);
    }
    @PostMapping("/create")
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);

    }
    @PutMapping("/{username}")
    public UserResponse updateUser(@PathVariable String username,@RequestBody UserRequest userRequest){
      return userService.updateUserByUsername(username,userRequest);
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username){
        if(userService.deleteUserByUsername(username)){
            return ResponseEntity.ok("user deleted successfully!");
        }else {
            return ResponseEntity.badRequest().body("an error occurred!");
        }
    }
}


