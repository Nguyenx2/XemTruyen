package com.example.xemtruyen.services.user;

import com.example.xemtruyen.dtos.user.ChangePasswordRequest;
import com.example.xemtruyen.dtos.user.UserUpdateRequest;
import com.example.xemtruyen.exceptions.BadRequestException;
import com.example.xemtruyen.exceptions.DataNotFoundException;
import com.example.xemtruyen.models.User;
import com.example.xemtruyen.reponses.user.UserPageResponse;
import com.example.xemtruyen.reponses.user.UserResponse;
import com.example.xemtruyen.repositories.UserRepository;
import com.example.xemtruyen.utils.MapperUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @PostAuthorize("returnObject.email == authentication.name")
    public UserResponse updateUser(long id, UserUpdateRequest request) {
        User user = findById(id);
        user.setFullName(request.getFullName());
        return MapperUtil.toResponse(userRepository.save(user), UserResponse.class);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserPageResponse list(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<User> users = userRepository.search(keyword, pageable);
        List<UserResponse> userResponses = MapperUtil.toDTOS(users, UserResponse.class);
        return UserPageResponse.of(userResponses, userResponses.size());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void blockOrEnable(long id, boolean active) {
        User user = findById(id);
        user.setActive(active);
        userRepository.save(user);
    }

    @Override
    @PostAuthorize("returnObject.email == authentication.name")
    public UserResponse details(long id) {
        User user = findById(id);
        return MapperUtil.toResponse(user, UserResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    @PostAuthorize("returnObject.email == authentication.name")
    public void changePassword(long id, ChangePasswordRequest request) {
        User user = findById(id);
        if (!passwordEncoder.matches(user.getPassword(), request.getPassword())) {
            throw new BadRequestException("Invalid old password");
        }
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Password not match");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id = " + id));
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with email = " + email));
    }

    private boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }
}
