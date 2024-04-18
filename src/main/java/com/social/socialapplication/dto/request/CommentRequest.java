package com.social.socialapplication.dto.request;

import com.social.socialapplication.entity.Comment;

import java.time.LocalDateTime;

public record CommentRequest(String commentText) {
    public static Comment convertCommentResponseToComment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setCommentsText(commentRequest.commentText);
        comment.setCreatedComment(LocalDateTime.now());
        return comment;
    }

}
