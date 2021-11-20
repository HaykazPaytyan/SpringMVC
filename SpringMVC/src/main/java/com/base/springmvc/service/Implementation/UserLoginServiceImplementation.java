package com.base.springmvc.service.Implementation;

import com.base.springmvc.domain.User;
import com.base.springmvc.repository.UserLoginRepository;
import com.base.springmvc.service.interfaces.UserLoginService;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImplementation implements UserLoginService {

    UserLoginRepository repository;

    public UserLoginServiceImplementation(UserLoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public User attempt(User user) {
        User user1 = repository.findByEmail(user.getEmail());
        return user1;
    }
}
