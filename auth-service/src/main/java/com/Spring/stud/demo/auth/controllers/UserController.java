package com.Spring.stud.demo.auth.controllers;

import com.Spring.stud.demo.api.dtos.ProfileDto;
import com.Spring.stud.demo.api.exceptions.ResourceNotFoundException;
import com.Spring.stud.demo.auth.model.User;
import com.Spring.stud.demo.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ProfileDto aboutCurrentUser(@RequestHeader String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя. Имя пользователя: " + username));
        return new ProfileDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
