package com.preguntation.controllers;

import com.preguntation.models.user;
import com.preguntation.repositories.RankingRepository;
import com.preguntation.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;
    private RankingRepository rankingRepository;

    @Autowired
    public UsersController(UsersRepository usersRepository, PasswordEncoder passwordEncoder, RankingRepository rankingRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.rankingRepository = rankingRepository;
    }

    @GetMapping("/")
    public String validLogin(@RequestHeader("Host") String host, Model model) {
        model.addAttribute("host", host);
        return "index";
    }

    @GetMapping("/register")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new user());
        return "/users/register";
    }

    @GetMapping("/profile")
    public String showProfile(@ModelAttribute user user, Model model) {
        model.addAttribute("user", usersRepository.findByEmail(user.getEmail()));
        return "users/profile";
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "users/reset_password";
    }

    @PostMapping("/reset-password")
    public String setNewPassword(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "newPassword") String newPassword,
                                 @RequestParam(name = "newPasswordConfirm") String passwordConfirm) {

        user existingUser = usersRepository.findByEmail(email);
        existingUser.setPassword(passwordEncoder.encode(newPassword));
        usersRepository.save(existingUser);
        return "redirect:/ ";
    }





}
