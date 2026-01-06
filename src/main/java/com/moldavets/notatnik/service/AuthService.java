package com.moldavets.notatnik.service;

import com.moldavets.notatnik.exception.UserAlreadyExistException;
import com.moldavets.notatnik.model.AuthRequest;
import com.moldavets.notatnik.model.NotepadUser;
import com.moldavets.notatnik.repository.NotepadUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final String TOKEN_PREFIX = " ";
    private static final String TOKEN_SUFFIX = " ";

    private final NotepadUserRepository notepadUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public void register(AuthRequest authRequest) {
        if (notepadUserRepository.existsByUsername(authRequest.getEmail())) {
            throw new UserAlreadyExistException(
                    String.format("User with email - %s already exists", authRequest.getEmail()));
        }
        var notepadUser = new NotepadUser();
        notepadUser.setUsername(authRequest.getEmail());
        notepadUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        notepadUserRepository.save(notepadUser);
    }

    public String login(AuthRequest authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        return TOKEN_PREFIX + jwtService.generateToken(authRequest.getEmail()) + TOKEN_SUFFIX;
    }

}
