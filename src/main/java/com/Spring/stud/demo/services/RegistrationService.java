package com.Spring.stud.demo.services;

import com.Spring.stud.demo.api.RegistrationRepository;
import com.Spring.stud.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    public User save(User user) {
        return registrationRepository.save(user);
    }
}
