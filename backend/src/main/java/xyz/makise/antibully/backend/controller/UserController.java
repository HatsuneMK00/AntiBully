package xyz.makise.antibully.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.makise.antibully.backend.entity.User;
import xyz.makise.antibully.backend.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/user")
@ResponseBody
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    User registerUser(@RequestBody Map<String,String> params) {
        User user = new User();
        user.setName(params.get("username"));
        user.setPassword(params.get("password"));
        int retVal = userService.registerUser(user);
        if (retVal == 1) {
            return user;
        } else {
            return null;
        }
    }
}
