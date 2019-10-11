package com.karim.facebookapplication.controller;

import com.karim.facebookapplication.model.Comment;
import com.karim.facebookapplication.model.Post;
import com.karim.facebookapplication.model.User;
import com.karim.facebookapplication.service.CommentService;
import com.karim.facebookapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.time.LocalDate;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService ;
    @Autowired
    private PostService postService ;

    @RequestMapping("/")
    public String getComment(Model model , HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session");
        model.addAttribute("comments", commentService.findAll());
        model.addAttribute("user" , user);
        return "home";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("comment")Comment comment , Model model , HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session");
        comment.setUser(user);
        comment.setLocalDate(LocalDate.now());
        commentService.save(comment);
        return "redirect:/posts/";
    }
}
