package com.preguntation.controllers;

import com.preguntation.models.user;
import com.preguntation.repositories.RankingRepository;
import com.preguntation.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


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

    @PostMapping("/register")
    public ResponseEntity<?> signup(@Valid user user,
                                    Errors validation,
                                    Model model){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/");
    //        return ResponseEntity.ok().build();
        if(validation.hasErrors())
            model.addAttribute("errors", validation);
        return new ResponseEntity<String>(headers,HttpStatus.FOUND);
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
