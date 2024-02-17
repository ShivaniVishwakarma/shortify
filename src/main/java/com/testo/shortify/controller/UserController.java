package com.testo.shortify.controller;

import com.testo.shortify.entity.User;
import com.testo.shortify.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        User newUser = userService.addUser(user);
        return newUser.getUsername();
    }

    //Created only for testing purpose
    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


}
