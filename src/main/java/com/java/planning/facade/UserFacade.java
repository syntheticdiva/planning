package com.java.planning.facade;

import com.java.planning.dto.*;

public interface UserFacade {
    UserDto registerUser(RegisterRequest request);
    UserDto registerAdmin(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    UserDto updateUser(Long id, UserUpdateDto dto);
    void deleteUser(Long id);
    UserDto getUserById(Long id);
}