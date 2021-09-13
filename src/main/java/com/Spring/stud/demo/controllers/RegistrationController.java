package com.Spring.stud.demo.controllers;

import com.Spring.stud.demo.model.Role;
import com.Spring.stud.demo.model.User;
import com.Spring.stud.demo.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping()
    public User save(@RequestBody User user) {
        //TODO get role from DB - do not make new one
        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        Collection<Role> roleList = new ArrayList<>();
        roleList.add(userRole);
        String newPassword = bCryptPasswordEncoder.encode(user.getPassword());

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(newPassword);
        newUser.setEmail(user.getEmail());
        newUser.setRoles(roleList);

        registrationService.save(newUser);
        return newUser;
    }
}
