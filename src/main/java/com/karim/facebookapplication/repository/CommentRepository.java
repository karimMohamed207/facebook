package com.karim.facebookapplication.repository;

import com.karim.facebookapplication.model.Comment;
import com.karim.facebookapplication.model.Post;
import com.karim.facebookapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment , Long> {
    Comment findByUser(User user);
    Comment findByPost(Post post);
}
