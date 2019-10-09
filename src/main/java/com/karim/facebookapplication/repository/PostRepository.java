package com.karim.facebookapplication.repository;

import com.karim.facebookapplication.model.Post;
import com.karim.facebookapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post , Long> {
    List<Post> findByUser(User user);
}
