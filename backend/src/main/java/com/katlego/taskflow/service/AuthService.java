package com.katlego.taskflow.service;

import com.katlego.taskflow.dto.LoginRequest;
import com.katlego.taskflow.dto.LoginResponse;
import com.katlego.taskflow.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}