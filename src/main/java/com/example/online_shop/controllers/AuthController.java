package com.example.online_shop.controllers;

import com.example.online_shop.dto.UserDto;
import com.example.online_shop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    //register
    @GetMapping("/register")
    public String register(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String create(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "register";
        }

        try {
            userService.create(userDto);
            model.addAttribute("message", "Registered successfully!");
            return "redirect:/login?email=" + userDto.getEmail();
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    //login
    @GetMapping("/login")
    public String login(@RequestParam(value = "email", required = false) String email, Model model) {
        model.addAttribute("email", email);
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        if (userService.verifyPassword(password, email)) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
}
