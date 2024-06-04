package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.UserCreationRequest;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.user.UserResponse;
import com.example.xemtruyen.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_USER_SUCCESS;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ApiResponse<UserResponse> create(@RequestBody UserCreationRequest request) {
        return ApiResponse.ofCreated(
                CREATE_USER_SUCCESS,
                userService.createUser(request)
        );
    }
}
