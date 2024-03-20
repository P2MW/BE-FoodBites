package com.p2mw.foodbites.controller;

import com.p2mw.foodbites.model.User;
import com.p2mw.foodbites.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateSeller(@PathVariable long id, @RequestBody User user){
        User updateUser = userService.updateUser(id, user);
        if (updateUser !=null){
            return ResponseEntity.ok(updateUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
