package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.auth.AuthenticationDTO;
import com.example.xemtruyen.dtos.user.UserCreationRequest;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.auth.AuthenticationResponse;
import com.example.xemtruyen.services.auth.AuthenticationService;
import com.example.xemtruyen.services.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.xemtruyen.constant.Constant.CodeValue.SUCCESS;
import static com.example.xemtruyen.constant.Constant.CommonConstants.DEFAULT_LANGUAGE;
import static com.example.xemtruyen.constant.Constant.CommonConstants.LANGUAGE;
import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_USER_SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.LOGIN_SUCCESSFULLY;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final MessageService messageService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationDTO request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<AuthenticationResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(LOGIN_SUCCESSFULLY, language))
                .data(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("/register")
    public ApiResponse<AuthenticationResponse> register(
            @RequestBody UserCreationRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<AuthenticationResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(CREATE_USER_SUCCESS, language))
                .data(authenticationService.register(request))
                .build();
    }
}
