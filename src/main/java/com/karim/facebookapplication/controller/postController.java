package com.karim.facebookapplication.controller;

import com.karim.facebookapplication.model.Post;
import com.karim.facebookapplication.model.User;
import com.karim.facebookapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.time.LocalDate;

@Controller
@RequestMapping("/posts")
public class postController {
    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String getPosts(Model model , HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session");
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("user" , user);
        return "home";
    }
    @RequestMapping(value = "/save" , method = RequestMethod.POST)
    public String addpost(Model model , Post post , HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session");
        post.setUser(user);
        post.setLocalDate(LocalDate.now());
        postService.save(post);
        return "redirect:/posts/";
    }
    @RequestMapping(value = "/get/{id}" , method = RequestMethod.GET)
    public String getOne(@PathVariable(value = "id") Long id , Model model){
         Post post = postService.findById(id);
         model.addAttribute("post" , post);
         return "update";
    }
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Long id , HttpServletRequest request , Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session");
        postService.deleteById(id);
        model.addAttribute("posts" , postService.findByUser(user));
        model.addAttribute("user" , user);
        return "profile";
    }
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public String updatepost(Model model , Post post , HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session");
        postService.update(post);
        model.addAttribute("posts" , postService.findByUser(user));
        model.addAttribute("user" , user);
        return "profile";
    }

}
