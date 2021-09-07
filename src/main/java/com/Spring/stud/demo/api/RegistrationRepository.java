package com.Spring.stud.demo.api;

import com.Spring.stud.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationRepository extends JpaRepository<User, Long> {
}
