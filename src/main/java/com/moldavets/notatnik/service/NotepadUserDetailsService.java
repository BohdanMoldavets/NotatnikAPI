package com.moldavets.notatnik.service;

import com.moldavets.notatnik.repository.NotepadUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotepadUserDetailsService implements UserDetailsService {

    private final NotepadUserRepository notepadUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var notepadUser = notepadUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return User.withUsername(notepadUser.getUsername())
                .password(notepadUser.getPassword())
                .roles("USER")
                .build();
    }
}
