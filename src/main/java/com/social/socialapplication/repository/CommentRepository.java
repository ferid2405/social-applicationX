package com.social.socialapplication.repository;

import com.social.socialapplication.entity.Comment;
import com.social.socialapplication.entity.Post;
import com.social.socialapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findByUser(User user);
}
