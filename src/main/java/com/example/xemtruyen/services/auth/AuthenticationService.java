package com.example.xemtruyen.services.auth;

import com.example.xemtruyen.dtos.auth.AuthenticationDTO;
import com.example.xemtruyen.dtos.user.UserCreationRequest;
import com.example.xemtruyen.reponses.auth.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationDTO request);
    AuthenticationResponse register(UserCreationRequest request);
}
