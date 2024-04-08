package com.social.socialapplication.dto.request;

import com.social.socialapplication.entity.Post;
import com.social.socialapplication.entity.User;

import java.time.LocalDateTime;

public record PostRequest(
        String postText,
        LocalDateTime createdPost
) {
    public  static Post convertPostRequestToPost(PostRequest postRequest){
        Post post=new Post();
        post.setPostText(postRequest.postText);
        post.setCreatedPost(LocalDateTime.now());

        return post;
}}
