package com.karim.facebookapplication.bootstrap;

import com.karim.facebookapplication.model.Comment;
import com.karim.facebookapplication.model.Post;
import com.karim.facebookapplication.model.User;
import com.karim.facebookapplication.service.CommentService;
import com.karim.facebookapplication.service.PostService;
import com.karim.facebookapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class devBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService ;
    @Autowired
    private PostService postService ;
    @Autowired
    private CommentService commentService ;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }
    public void init(){
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@admin.com");
        user.setPassword("admin");
        userService.save(user);
        User user1 = new User();
        user1.setUsername("karim");
        user1.setEmail("kmaged207@gmail.com");
        user1.setPassword("koko");
        userService.save(user1);

        Post post = new Post();
        post.setContant("hello my name is karim ");
        post.setUser(user);
        post.setLocalDate(LocalDate.now());
        postService.save(post);

        Comment comment = new Comment();
        comment.setContant("this is first comment");
        comment.setPost(post);
        comment.setLocalDate(LocalDate.now());
        comment.setUser(user);
        commentService.save(comment);

    }
}
