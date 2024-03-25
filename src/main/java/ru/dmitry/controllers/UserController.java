package ru.dmitry.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmitry.model.User;
import ru.dmitry.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private static UserService userService = new UserService();

    @GetMapping("/getall")
    public String getAllUsers(@RequestParam(value = "id", required = false) Integer id, Model model) {
        userService = new UserService();
        if (id != null) {
            model.addAttribute("user", userService.getUser(id));
            return "/showone";
        } else {
            model.addAttribute("users", userService.getAllUsers());
            return "/showusers";
        }
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) Integer id) {
        userService.removeUserById(id);
        return "redirect:/users/getall";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user) {
        return "/new";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users/getall";
    }

    @GetMapping("/change")
    public String changeUser(@ModelAttribute("user") User user) {
        return "/change";
    }

    @PatchMapping()
    public String editUser(@ModelAttribute("user") User user, @RequestParam(value = "id", required = false) Integer id) {
        userService.changeUser(id, user);
        return "redirect:/users/getall";
    }
}
