package com.social.socialapplication.controller;

import com.social.socialapplication.dto.request.CommentRequest;
import com.social.socialapplication.dto.response.CommentResponse;
import com.social.socialapplication.services.CommentService;
import com.social.socialapplication.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {
    private  final CommentService commentService;
    @GetMapping("/{postId}/comment")
    public List<CommentResponse> getAllCommentByPostId(@PathVariable Long postId) {
        return commentService.getAllCommentByPostId(postId);
    }

    @GetMapping("/{username}/comments")
    public List<CommentResponse> getAllCommentByUsername(@PathVariable String username) {
        return commentService.getAllCommentByUsername(username);
    }

    @PostMapping("/{username}/{postId}/comment")
    public CommentResponse createComment(@PathVariable String username, @PathVariable Long postId, @RequestBody CommentRequest commentRequest) {
        return commentService.createComment(username, postId, commentRequest);
    }

    @PutMapping("/{username}/{postId}/{commentId}")
    public CommentResponse updateComment(@PathVariable String username, @PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        return commentService.updateComment(username, postId, commentId, commentRequest);
    }

    @DeleteMapping("/{username}/{postId}/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String username, @PathVariable Long postId, @PathVariable Long commentId) {
        if (commentService.deleteComment(username, postId, commentId)) {
            return ResponseEntity.ok("Comment deleted");
        } else {
            return ResponseEntity.ok("Comment not deleted");
        }
    }
}

