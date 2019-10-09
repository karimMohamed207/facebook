package com.karim.facebookapplication.service;

import com.karim.facebookapplication.model.Post;
import com.karim.facebookapplication.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {
    public Post save(Post post);
    public List<Post> findAll();
    public List<Post> findByUser(User user);
    public Post findById(Long id);
    public void deleteById(Long id);
    public Post update( Post post);
}
