package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.auth.AuthenticationDTO;
import com.example.xemtruyen.dtos.auth.IntrospectRequest;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.auth.AuthenticationResponse;
import com.example.xemtruyen.reponses.auth.IntrospectResponse;
import com.example.xemtruyen.services.auth.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

import static com.example.xemtruyen.constant.Constant.MessageException.LOGIN_SUCCESSFULLY;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationDTO request) {
        return ApiResponse.ofSuccess(
                LOGIN_SUCCESSFULLY,
                authenticationService.authenticate(request)
        );
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return ApiResponse.ofSuccess(
                LOGIN_SUCCESSFULLY,
                authenticationService.introspect(request)
        );
    }
}
