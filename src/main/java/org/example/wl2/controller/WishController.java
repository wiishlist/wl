package org.example.wl2.controller;

import org.example.wl2.model.WishlistModel;
import org.example.wl2.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WishController {
    private final WishService service;

    public WishController(WishService service) {
        this.service = service;
    }

    @GetMapping("/wishes")
    public String getWish(Model model) {
        model.addAttribute("wish", service.getAll());
        return "wishList";
    }

    @GetMapping("/add")
    public String addWish(Model model) {
        model.addAttribute("wish", new WishlistModel());
        return "addedWish";
    }

    @PostMapping("/add")
    public String saveWish(WishlistModel model){
        service.addWish(model);
        return "redirect:/wishes";
    }

    @GetMapping("/wishes/{id}/update")
    public String updateWish(@PathVariable int id, Model model){
        model.addAttribute("wish", service.getById(id));
        return "update";
    }

    @PostMapping("/update")
    public String saveUpdate(@ModelAttribute, WishlistModel model){
        service.updateWish(model.getid(),model);
        return "redirect:/wishes";
    }

    @PostMapping("/delete/{id}")
    public String deleteWish(@PathVariable int id){
        service.delete(id);
        return "redirect:/wishes";
    }

}


