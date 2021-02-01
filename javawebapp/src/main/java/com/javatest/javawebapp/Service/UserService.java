package com.javatest.javawebapp.Service;

import com.javatest.javawebapp.model.User;
import com.javatest.javawebapp.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}
