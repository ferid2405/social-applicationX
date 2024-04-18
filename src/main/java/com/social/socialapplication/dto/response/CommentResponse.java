package com.social.socialapplication.dto.response;

import com.social.socialapplication.entity.Comment;

import java.time.LocalDateTime;
public record CommentResponse(String username,
                              Long postId,
                              String commentText,
                              LocalDateTime createdDate) {
    public static CommentResponse converteCommnetToCommentResponse(Comment comment) {
        return new CommentResponse(comment.getUser().getUsername(), comment.getPost().getPostId(), comment.getCommentsText(), comment.getCreatedComment());
    }
}
