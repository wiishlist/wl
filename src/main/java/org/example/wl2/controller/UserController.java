package org.example.wl2.controller;

import org.example.wl2.model.User;
import org.example.wl2.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    //Registrere en ny bruger
   @PostMapping("/register")
    public String register (@ModelAttribute User user, HttpSession session, Model model){
        try {
            User newUser = service.registerUser(user);
            session.setAttribute("userId", newUser.getId());
            return "redirect:/wishes";
        } catch (IllegalArgumentException e) {
            model.addAttribute("registerError", e.getMessage());
            return "register";
        }
   }

    //logger brugeren ind
   @PostMapping("/login")
    public String login (@RequestParam String username,
                         @RequestParam String password,
                         HttpSession session,
                         Model model){

        if (service.login(username, password)) {
            User user = service.getUsername(username);
            session.setAttribute("userId",user.getId());
            return "redirect:/wishes";
        } else {
            model.addAttribute("loginError", "Brugernavn eller password er forkert!");
            return "login";
        }
   }

   @GetMapping("/{username}")
    public User getUser(@PathVariable String username){
        return service.getUsername(username);
   }

   @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email){
        return service.getEmail(email);
   }

   @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
   }

}
