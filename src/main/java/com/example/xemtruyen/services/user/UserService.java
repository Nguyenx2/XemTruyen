package com.example.xemtruyen.services.user;

import com.example.xemtruyen.dtos.UserCreationRequest;
import com.example.xemtruyen.reponses.user.UserResponse;

public interface UserService {
    UserResponse createUser(UserCreationRequest request);
}
