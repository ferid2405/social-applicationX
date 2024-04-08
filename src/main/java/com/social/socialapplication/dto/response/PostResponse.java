package com.social.socialapplication.dto.response;

import com.social.socialapplication.entity.Post;

import java.time.LocalDateTime;

public record PostResponse(
        String username,
        Long postId,
        String postText,
        LocalDateTime createdPost


) {
    public static PostResponse convertPostToPostResponse(Post posts) {
        return new PostResponse(posts.getUser().getUsername(),
                posts.getPostId(),
                posts.getPostText(),
                posts.getCreatedPost());

}}
