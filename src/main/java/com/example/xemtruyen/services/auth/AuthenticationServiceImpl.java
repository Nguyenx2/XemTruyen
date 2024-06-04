package com.example.xemtruyen.services.auth;

import com.example.xemtruyen.dtos.auth.AuthenticationDTO;
import com.example.xemtruyen.dtos.auth.IntrospectRequest;
import com.example.xemtruyen.exceptions.BadRequestException;
import com.example.xemtruyen.models.User;
import com.example.xemtruyen.reponses.auth.AuthenticationResponse;
import com.example.xemtruyen.reponses.auth.IntrospectResponse;
import com.example.xemtruyen.repositories.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    protected static final String SIGNER_KEY =
            "i6Q9M2TJUr1rUc4KLzwtuT4kaVqSdz+iVVwU6HyjesbJa+xsLw2k+jEDVPW4Z5ek\n";
    @Override
    public AuthenticationResponse authenticate(AuthenticationDTO request) {
        User user = userRepository.findUserByEmail(request.getEmail());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new BadRequestException("Invalid");
        }

        String token = generateToken(request.getEmail());
        return AuthenticationResponse.of(token, true);
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest introspect) throws JOSEException, ParseException {
        String token = introspect.getToken();

        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(jwsVerifier);

        return IntrospectResponse.of(verified && expityTime.after(new Date()));
    }

    private String generateToken(String email) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("xemtruyen.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customClaim", "Custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
