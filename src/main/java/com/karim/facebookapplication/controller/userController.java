package com.karim.facebookapplication.controller;

import com.karim.facebookapplication.model.User;
import com.karim.facebookapplication.service.PostService;
import com.karim.facebookapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
public class userController {

    @Autowired
    private UserService userService ;
    @Autowired
    private PostService postService ;

    @RequestMapping(value = {"","/","/index","/index.html"} , method = RequestMethod.GET )
    public String index(){
        return "index.html";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String register(){
        return "register.html";
    }

    @RequestMapping(value = "/adduser" , method = RequestMethod.POST )
    public String add(@ModelAttribute("user") User user , HttpServletRequest request , Model model){
        userService.save(user);
        HttpSession session = request.getSession();
        session.setAttribute("session" , user);
        model.addAttribute("user" , user);
        return "redirect:/posts/";
    }

    @RequestMapping(value = {"/login/{username}/{password}" , "/login"} , method = RequestMethod.POST)
    public String home(@PathParam("username") String username , @PathParam("password") String password ,
                       HttpServletRequest request , HttpServletResponse response, Model model){
        User user = userService.findByUsernameAndPassword(username , password);
        if (user == null)
            return "redirect:/";
        HttpSession session = request.getSession();
        session.setAttribute("session" , user);
        model.addAttribute("user" , user);
        return "redirect:/posts/";
    }
    @RequestMapping(value = "/find/{username}" , method = RequestMethod.POST)
    public String findByUsername(Model model , User user ,@PathParam("username") String username , HttpServletRequest request){
        user = userService.findByUsername(username);
        model.addAttribute("user" , user);
        if (user == null){
            HttpSession session = request.getSession();
            user =(User) session.getAttribute("session");
            model.addAttribute("user" , user);
            return "redirect:/posts/";
        }
        User user1 = userService.findByUsername(username);
        model.addAttribute("posts" , postService.findByUser(user1));
        return "find";
    }
    @RequestMapping("/profile/{username}" )
    public String myprofile(Model model , User user ,@PathParam("username") String username , HttpServletRequest request){
        HttpSession session = request.getSession();
        user =(User) session.getAttribute("session");
        model.addAttribute("posts" , postService.findByUser(user));
        model.addAttribute("user" , user);
        return "profile";
    }
    @RequestMapping(value = "/getpost"  , method = RequestMethod.POST)
    public String myprofile(User user , HttpServletRequest request , Model model){
        HttpSession session = request.getSession();
        user =(User) session.getAttribute("session");
        model.addAttribute("user" , user);
        return "redirect:/posts/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("session");
        return "redirect:/";
    }


}
