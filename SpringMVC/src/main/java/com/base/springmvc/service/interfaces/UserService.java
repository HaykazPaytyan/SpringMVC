package com.base.springmvc.service.interfaces;

import com.base.springmvc.domain.Permission;
import com.base.springmvc.domain.User;

import java.util.List;

public interface UserService {
    User findById(long id);
    boolean hasRole(String role);
    boolean hasPermission(String permission);
    boolean hasPermissionThroughRole(String permission);

}
