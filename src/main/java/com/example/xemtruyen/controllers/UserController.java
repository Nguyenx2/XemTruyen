package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.user.ChangePasswordRequest;
import com.example.xemtruyen.dtos.user.UserUpdateRequest;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.user.UserPageResponse;
import com.example.xemtruyen.reponses.user.UserResponse;
import com.example.xemtruyen.services.message.MessageService;
import com.example.xemtruyen.services.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.xemtruyen.constant.Constant.CodeValue.SUCCESS;
import static com.example.xemtruyen.constant.Constant.CommonConstants.*;
import static com.example.xemtruyen.constant.Constant.MessageException.*;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageService messageService;

    @GetMapping("/list")
    public ApiResponse<UserPageResponse> list(
            @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<UserPageResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(userService.list(keyword, page, size))
                .build();
    }

    @GetMapping("/details/{id}")
    public ApiResponse<UserResponse> getUserDetail(
            @Valid @PathVariable long id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<UserResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(userService.details(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> update(
            @Valid @PathVariable long id,
            UserUpdateRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<UserResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(UPDATE_USER_SUCCESS, language))
                .data(userService.updateUser(id, request))
                .build();
    }

    @PutMapping("/block/{id}/{active}")
    public ApiResponse<Void> blockOrEnable(
            @PathVariable Long id,
            @PathVariable int active,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        userService.blockOrEnable(id, active > 0);
        String message = active > 0 ? ENABLED_USER_SUCCESS : BLOCKED_USER_SUCCESS;
        return ApiResponse.<Void>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(message, language))
                .build();
    }

    @PutMapping("/change-password/{id}")
    public ApiResponse<Void> changePassword(
            @PathVariable long id,
            @RequestBody ChangePasswordRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        userService.changePassword(id, request);
        return ApiResponse.<Void>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(CHANGE_PASSWORD_SUCCESS, language))
                .build();
    }
}
