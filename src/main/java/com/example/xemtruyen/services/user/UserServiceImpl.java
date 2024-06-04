package com.example.xemtruyen.services.user;

import com.example.xemtruyen.dtos.UserCreationRequest;
import com.example.xemtruyen.exceptions.BadRequestException;
import com.example.xemtruyen.exceptions.ConflictException;
import com.example.xemtruyen.models.User;
import com.example.xemtruyen.reponses.user.UserResponse;
import com.example.xemtruyen.repositories.UserRepository;
import com.example.xemtruyen.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("User already exists with email = " + request.getEmail());
        }

        User user = MapperUtil.toResponse(request, User.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return MapperUtil.toResponse(userRepository.save(userRepository.save(user)), UserResponse.class);
    }
}
