package com.moldavets.notatnik.repository;

import com.moldavets.notatnik.model.NotepadUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotepadUserRepository extends JpaRepository<NotepadUser, Long> {

    Optional<NotepadUser> findByUsername(String username);
    boolean existsByUsername(String username);

}
