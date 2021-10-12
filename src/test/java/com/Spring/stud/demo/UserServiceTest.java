package com.Spring.stud.demo;

import com.Spring.stud.demo.api.UserRepository;
import com.Spring.stud.demo.model.User;
import com.Spring.stud.demo.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest (classes = UserService.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findUserTest() {
        User userFromDB = new User();
        userFromDB.setUsername("user0");
        userFromDB.setEmail("user0@email.com");
        Mockito.doReturn(Optional.of(userFromDB))
                .when(userRepository)
                .findByUsername("user0");
        User user0 = userService.findByUsername("user0").get();
        Assertions.assertNotNull(user0);
        Assertions.assertEquals("user0@email.com", user0.getEmail());
        Mockito.verify(userRepository, Mockito.times(1))
                .findByUsername(ArgumentMatchers.eq("user0"));
    }
}
