package com.example.xemtruyen.services.auth;

import com.example.xemtruyen.dtos.auth.AuthenticationDTO;
import com.example.xemtruyen.dtos.user.UserCreationRequest;
import com.example.xemtruyen.exceptions.BadRequestException;
import com.example.xemtruyen.exceptions.DataNotFoundException;
import com.example.xemtruyen.models.Role;
import com.example.xemtruyen.models.Token;
import com.example.xemtruyen.models.TokenType;
import com.example.xemtruyen.models.User;
import com.example.xemtruyen.reponses.auth.AuthenticationResponse;
import com.example.xemtruyen.repositories.RoleRepository;
import com.example.xemtruyen.repositories.TokenRepository;
import com.example.xemtruyen.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.example.xemtruyen.models.Role.USER;
import static com.example.xemtruyen.utils.ValidationUtils.isValidEmail;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;

    @Override

    public AuthenticationResponse authenticate(AuthenticationDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with email = " + request.getEmail()));

        String jwtToken = jwtService.generateToken(user);

        revokedAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse register(UserCreationRequest request) {
        if (userRepository.existsUserByEmail(request.getEmail())) {
            throw new BadRequestException("User already exists");
        }
        if (!request.getPassword().equals(request.getRetypePassword())) {
            throw new BadRequestException("Password not match");
        }
        if (!isValidEmail(request.getEmail())) {
            throw new BadRequestException("Invalid email");
        }
        Role role = roleRepository.findRoleByName(USER);
        User user = User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(request.getPassword())
                .role(role)
                .isActive(true)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    private void revokedAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }
}
