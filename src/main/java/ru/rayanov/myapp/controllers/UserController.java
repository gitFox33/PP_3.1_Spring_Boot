package ru.rayanov.myapp.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.rayanov.myapp.models.User;
import ru.rayanov.myapp.service.UserService;


@Controller
public class UserController {
    private final UserService userService;
    private String index = "redirect:/index";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String helloPage(Model model) {
        return "hello";
    }

    @GetMapping("/index")
    public String indexPage(Model model) {

        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }


    @GetMapping("/new")
    public String newPage(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.createUser(user);
        return index;
    }

    @GetMapping("/{id}/edit")
    public String editPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }


    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.editUser(id, user);
        return index;
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return index;
    }
}




