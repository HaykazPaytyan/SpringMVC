package com.base.springmvc.service.interfaces;

import com.base.springmvc.domain.User;

public interface UserLoginService {
    User attempt(User user);
}
