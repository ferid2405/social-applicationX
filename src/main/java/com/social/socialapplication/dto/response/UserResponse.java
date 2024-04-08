package com.social.socialapplication.dto.response;

import com.social.socialapplication.entity.User;

import java.time.LocalDate;

public record UserResponse(
        String username,
        String name,
        String surname,
        String email,
        LocalDate birthdate
) {
    public static UserResponse convertUserToUserResponse(User savedUser) {
   return      new UserResponse(savedUser.getUsername(),
                savedUser.getName(),
                savedUser.getSurname(),
                savedUser.getEmail(),
                savedUser.getBirthdate());
    }

}
