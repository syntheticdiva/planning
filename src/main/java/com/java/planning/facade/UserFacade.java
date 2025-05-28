package com.java.planning.facade;

import com.java.planning.dto.*;
import com.java.planning.entity.User;
import com.java.planning.enums.Role;
import com.java.planning.mapper.UserMapper;
import com.java.planning.security.JwtTokenProvider;
import com.java.planning.service.CustomUserDetailsService;
import com.java.planning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public UserDto registerUser(RegisterRequest request) {
        return userMapper.toDto(
                userService.createUser(request, Role.USER)
        );
    }

    public UserDto registerAdmin(RegisterRequest request) {
        return userMapper.toDto(
                userService.createUser(request, Role.ADMIN)
        );
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails user = customUserDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public UserDto updateUser(Long id, UserUpdateDto dto) {
        User existingUser = userService.getUserById(id);
        userMapper.updateEntityFromUpdateDto(existingUser, dto);
        return userMapper.toDto(userService.updateUser(existingUser));
    }
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    public UserDto getUserById(Long id) {
        return userMapper.toDto(userService.getUserById(id));
    }
}