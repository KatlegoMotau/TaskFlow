package com.katlego.taskflow.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.katlego.taskflow.dto.LoginRequest;
import com.katlego.taskflow.dto.LoginResponse;
import com.katlego.taskflow.dto.RegisterRequest;
import com.katlego.taskflow.entity.Role;
import com.katlego.taskflow.entity.User;
import com.katlego.taskflow.repository.UserRepository;
import com.katlego.taskflow.security.JwtService;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository repository,
                           PasswordEncoder passwordEncoder,
                        JwtService jwtService,
                    AuthenticationManager authenticationManager) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String register(RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        // Encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.USER);

        repository.save(user);

        return "User registered successfully.";
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}
