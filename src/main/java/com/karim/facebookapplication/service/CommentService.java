package com.karim.facebookapplication.service;

import com.karim.facebookapplication.model.Comment;
import com.karim.facebookapplication.model.Post;
import com.karim.facebookapplication.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public Comment save(Comment post);
    public List<Comment> findAll();
    public List<Comment> findByUser(User user);
    public List<Comment> findByPost(Post post);
    public Comment findById(Long id);
    public void deleteById(Long id);
    public Comment update( Comment comment);
}
