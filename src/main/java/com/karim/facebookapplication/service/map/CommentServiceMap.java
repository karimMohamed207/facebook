package com.karim.facebookapplication.service.map;

import com.karim.facebookapplication.model.Comment;
import com.karim.facebookapplication.model.Post;
import com.karim.facebookapplication.model.User;
import com.karim.facebookapplication.repository.CommentRepository;
import com.karim.facebookapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceMap implements CommentService {

    @Autowired
    private CommentRepository commentRepository ;

    @Override
    public Comment save(Comment post) {
        return commentRepository.save(post);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findByUser(User user) {
        return (List<Comment>) commentRepository.findByUser(user);
    }

    @Override
    public List<Comment> findByPost(Post post) {
        return (List<Comment>) commentRepository.findByPost(post);
    }

    @Override
    public Comment findById(Long id) {
        return findById(id);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }
}
