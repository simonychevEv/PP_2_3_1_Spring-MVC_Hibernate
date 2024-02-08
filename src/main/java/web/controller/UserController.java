package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/user_info")
    public String getCreateUser(Model model) {
        model.addAttribute("user", new User());
        return "user_info";
    }

    @PostMapping("/user_info")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/update_user")
    public String getUpdateUser(@RequestParam("userId") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user_info";
    }

    @DeleteMapping("/delete_user")
    public String deleteUser(@RequestParam("userId") int id){
        userService.deleteUser(id);
        return "redirect:/";
    }
}
