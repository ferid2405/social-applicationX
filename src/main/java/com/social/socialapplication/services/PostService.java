package com.social.socialapplication.services;

import com.social.socialapplication.dto.request.PostRequest;
import com.social.socialapplication.dto.response.PostResponse;
import com.social.socialapplication.entity.Post;
import com.social.socialapplication.entity.User;
import com.social.socialapplication.exception.PostNotFound;
import com.social.socialapplication.exception.UserNotFound;
import com.social.socialapplication.repository.PostRepository;
import com.social.socialapplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public List<PostResponse> getAllPost() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
        return posts.stream().map(PostResponse::convertPostToPostResponse).collect(Collectors.toList());
    }

    public List<PostResponse> getAllPostByUsername(String username) {
        User user = userRepository.findByUsername(username).
                orElseThrow(()->new UserNotFound("Istifadəçi tapılmadı"));
        List<Post>posts=postRepository.findPostByUser(user);
        return posts.stream().map(PostResponse::convertPostToPostResponse).collect(Collectors.toList());
    }


    public PostResponse createPostByUsername(String username, PostRequest postRequest) {
        User user = userRepository.findByUsername(username).
                orElseThrow(()->new UserNotFound("Istifadəçi tapılmadı"));
        Post post=PostRequest.convertPostRequestToPost(postRequest);
        post.setUser(user);
        Post postSaved=postRepository.save(post);
        return PostResponse.convertPostToPostResponse(postSaved);
    }
    public Boolean deletePostByPostId(Long postId) {
        try {
            Post foundPost = postRepository.findById(postId).
                    orElseThrow(()->new PostNotFound("Belə bir post tapılmadı"));
            postRepository.delete(foundPost);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public PostResponse updatePost(Long postId, PostRequest postRequest) {
        Post foundPost = postRepository.findById(postId).
                orElseThrow(()->new PostNotFound("Belə bir post tapılmadı"));
        Optional.ofNullable(postRequest.postText()).ifPresent(foundPost::setPostText);
        Post savedPost = postRepository.save(foundPost);
        return PostResponse.convertPostToPostResponse(savedPost);
    }



}


