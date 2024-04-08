package com.social.socialapplication.services;

import com.social.socialapplication.dto.request.UserRequest;
import com.social.socialapplication.dto.response.UserResponse;
import com.social.socialapplication.entity.User;
import com.social.socialapplication.exception.UserNotFound;
import com.social.socialapplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.yaml.snakeyaml.nodes.Tag.STR;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User user=UserRequest.convertUserRequestToUser(userRequest);
        User savedUser=userRepository.save(user);
        return  UserResponse.convertUserToUserResponse(savedUser);

    }
    public List<UserResponse> getAllUser(){
        return userRepository.findAll().
                stream().
                map(UserResponse::convertUserToUserResponse).
                collect(Collectors.toList());
}

    @Transactional
    public UserResponse getOneUserByUsername(String username) {
        User foundUser = userRepository.findByUsername(username).
                orElseThrow(()->new UserNotFound("Istifadəçi tapılmadı"));
        return UserResponse.convertUserToUserResponse(foundUser);

    }   @Transactional
    public UserResponse updateUserByUsername(String username, UserRequest userRequest) {
        User foundUser = userRepository.findByUsername(username).
                orElseThrow(()->new UserNotFound("Istifadəçi tapılmadı"));
        User requestUser = UserRequest.convertUserRequestToUser(userRequest);
        User user = updateUser(foundUser, requestUser);
        User savedUser = userRepository.save(user);
        return UserResponse.convertUserToUserResponse(savedUser);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    protected User updateUser(User foundUser, User requestUser){
        Optional.ofNullable(requestUser.getName()).ifPresent(foundUser::setName);
        Optional.ofNullable(requestUser.getSurname()).ifPresent(foundUser::setSurname);
        Optional.ofNullable(requestUser.getBirthdate()).ifPresent(foundUser::setBirthdate);
        return foundUser;


}  public Boolean deleteUserByUsername(String username) {
        try {
            User foundUser = userRepository.findByUsername(username).
                    orElseThrow(()->new UserNotFound("Istifadəçi tapılmadı"));

            userRepository.delete(foundUser);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
