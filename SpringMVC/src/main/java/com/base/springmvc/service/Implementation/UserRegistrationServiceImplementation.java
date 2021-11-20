package com.base.springmvc.service.Implementation;

import com.base.springmvc.domain.User;
import com.base.springmvc.repository.UserRegistrationRepository;
import com.base.springmvc.service.interfaces.UserRegistrationService;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImplementation implements UserRegistrationService {

    UserRegistrationRepository repository;

    public UserRegistrationServiceImplementation(UserRegistrationRepository repository) {
        this.repository = repository;
    }

    @Override
    public User store(User user) {
        User user1 = repository.save(user);
        return user1;
    }
}
