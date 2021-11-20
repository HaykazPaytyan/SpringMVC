package com.base.springmvc.service.Implementation;

import com.base.springmvc.domain.User;
import com.base.springmvc.repository.UserRepository;
import com.base.springmvc.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    UserRepository repository;

    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findById(long id) {
        User user = repository.getById(id);
        return user;
    }

    @Override
    public boolean hasRole(String role) {
        return false;
    }

    @Override
    public boolean hasPermission(String permission) {
        return false;
    }

    @Override
    public boolean hasPermissionThroughRole(String permission) {
        return false;
    }


}
