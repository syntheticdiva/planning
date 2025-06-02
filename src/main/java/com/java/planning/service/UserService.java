package com.java.planning.service;

import com.java.planning.dto.RegisterRequest;
import com.java.planning.entity.User;
import com.java.planning.enums.Role;

public interface UserService {
    User createUser(RegisterRequest request, Role role);
    User getUserById(Long id);
    User updateUser(User user);
    void deleteUser(Long id);
}
