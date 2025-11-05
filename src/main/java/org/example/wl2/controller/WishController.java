package org.example.wl2.controller;

import jakarta.servlet.http.HttpSession;
import org.example.wl2.model.User;
import org.example.wl2.model.Wish;
import org.example.wl2.service.UserService;
import org.example.wl2.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class WishController {
    private final WishService service;
    private final UserService userService;

    public WishController(WishService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/wishes")
    public String showWishes(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        User user = userService.getById(userId);
        if(user == null) return "redirect:/login";

        model.addAttribute("username", user.getUser());
        model.addAttribute("wishes", service.getAllWishesByUser(userId));
        model.addAttribute("new_wish", new Wish());

        return "wishList";
    }


    @PostMapping("/add")
    public String addWish(HttpSession session, @ModelAttribute Wish wish) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        wish.setUserId(userId);
        service.addWish(wish);
        return "redirect:/wishes";
    }

   @GetMapping("/wishes/{id}/update")
    public String updateWish(@PathVariable int id, Model model){
        model.addAttribute("wish", service.getById(id));
        return "update";
    }

    @PostMapping("/update")
    public String saveUpdate(@ModelAttribute Wish model, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        Wish existingWish = service.getById(model.getId());
        if (Objects.equals(existingWish.getUserId(), userId)) {
            service.updateWish(model.getId(),model);
        }
        return "redirect:/wishes";
    }

    @PostMapping("/delete/{id}")
    public String deleteWish(@PathVariable int id, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        Wish wish = service.getById(id);
        if (Objects.equals(wish.getUserId(), userId)) {
            service.delete(id);
        }
        return "redirect:/wishes";
    }

    @GetMapping("/wishes/public/{userId}")
    public String showPublicWishlist(@PathVariable int userId, Model model) {
        User user = userService.getById(userId);
        if (user == null) {
            model.addAttribute("Error", "Brugeren findes ikke");
            return "error";
        }
        model.addAttribute("username", user.getUser());
        model.addAttribute("wishes", service.getAllWishesByUser(userId));
        return "publicWishList";

    }

}

