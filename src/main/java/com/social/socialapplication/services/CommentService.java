package com.social.socialapplication.services;

import com.social.socialapplication.dto.request.CommentRequest;
import com.social.socialapplication.dto.response.CommentResponse;
import com.social.socialapplication.entity.Comment;
import com.social.socialapplication.entity.Post;
import com.social.socialapplication.entity.User;
import com.social.socialapplication.exception.CommentNotFound;
import com.social.socialapplication.exception.PostNotFound;
import com.social.socialapplication.exception.UserNotFound;
import com.social.socialapplication.repository.CommentRepository;
import com.social.socialapplication.repository.PostRepository;
import com.social.socialapplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private  final CommentRepository commentRepository;
    private  final PostRepository postRepository;
    private final UserRepository userRepository;
    public List<CommentResponse> getAllCommentByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new PostNotFound("Belə bir post tapılmadı"));
        List<Comment> comments = commentRepository.findByPost(post);
        return comments.stream().map(CommentResponse::converteCommnetToCommentResponse).toList();
    }

    public List<CommentResponse> getAllCommentByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFound("Istifadəçi tapılmadı"));
        List<Comment> comments = commentRepository.findByUser(user);
        return comments.stream().map(CommentResponse::converteCommnetToCommentResponse).toList();
    }
    @Transactional
    public CommentResponse createComment(String username, Long postId, CommentRequest commentRequest) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFound("Istifadəçi tapılmadı"));
        Post post = postRepository.findById(postId).orElseThrow(()->new PostNotFound("Belə bir post tapılmadı"));
        Comment comment = CommentRequest.convertCommentResponseToComment(commentRequest);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return CommentResponse.converteCommnetToCommentResponse(savedComment);
    }

    @Transactional
    public CommentResponse updateComment(String username, Long postId, Long commentId, CommentRequest commentRequest) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFound("Istifadəçi tapılmadı"));
        Post post = postRepository.findById(postId).orElseThrow(()->new PostNotFound("Belə bir post tapılmadı"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new CommentNotFound("Belə bir comment tapılmadı"));
        if (comment.getUser().equals(user) && comment.getPost().equals(post)) {
            Optional.ofNullable(commentRequest.commentText()).ifPresent(comment::setCommentsText);
            Comment savedComment = commentRepository.save(comment);
            return CommentResponse.converteCommnetToCommentResponse(savedComment);
        }
        return null;
    }

    @Transactional
    public boolean deleteComment(String username, Long postId, Long commentId) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFound("Istifadəçi tapılmadı"));
        Post post = postRepository.findById(postId).orElseThrow(()->new PostNotFound("Belə bir post tapılmadı"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new CommentNotFound("Belə bir comment tapılmadı"));
        if (comment.getUser().equals(user) && comment.getPost().equals(post)) {
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }

}
