package ru.dmitry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmitry.model.User;
import ru.dmitry.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all")
    public String getAllUsers(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            model.addAttribute("user", userService.findOne(id));
            return "/showone";
        } else {
            model.addAttribute("users", userService.findAll());
            return "/showusers";
        }
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) Integer id) {
        userService.delete(id);
        return "redirect:/users/get-all";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user) {
        return "/new";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users/get-all";
    }

    @GetMapping("/change")
    public String changeUser(@ModelAttribute("user") User user) {
        return "/change";
    }

    @PatchMapping()
    public String editUser(@ModelAttribute("user") User user, @RequestParam(value = "id", required = false) Integer id) {
        userService.update(id, user);
        return "redirect:/users/get-all";
    }
}
