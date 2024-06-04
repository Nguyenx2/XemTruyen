package com.example.xemtruyen.services.auth;

import com.example.xemtruyen.dtos.auth.AuthenticationDTO;
import com.example.xemtruyen.dtos.auth.IntrospectRequest;
import com.example.xemtruyen.reponses.auth.AuthenticationResponse;
import com.example.xemtruyen.reponses.auth.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationDTO request);

    IntrospectResponse introspect(IntrospectRequest introspect) throws JOSEException, ParseException;
}
