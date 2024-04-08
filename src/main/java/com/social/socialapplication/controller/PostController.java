package com.social.socialapplication.controller;

import com.social.socialapplication.dto.request.PostRequest;
import com.social.socialapplication.dto.response.PostResponse;
import com.social.socialapplication.services.PostService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private  final PostService postService;
    @GetMapping("/all")
    public List<PostResponse>getAllPost(){
        return postService.getAllPost();
    }
    @GetMapping("/{username}all")
    public List<PostResponse>getAllPostByUsername(@PathVariable String username){
        return postService.getAllPostByUsername(username);
    }
    @PostMapping
    public PostResponse createPostByUsername(@PathVariable String username, @RequestBody PostRequest postRequest){
        return postService.createPostByUsername(username,postRequest);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        if (postService.deletePostByPostId(postId)) {
            return ResponseEntity.ok("Deleted post");
        } else {
            return ResponseEntity.badRequest().body("Post not found");
        }
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(postService.updatePost(postId, postRequest));
    }

    }


