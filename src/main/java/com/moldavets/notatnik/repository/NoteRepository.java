package com.moldavets.notatnik.repository;

import com.moldavets.notatnik.model.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    List<NoteEntity> getAllByCreatedBy(String createdBy);
    Optional<NoteEntity> getByNoteIdAndCreatedBy(Long id, String createdBy);
    Optional<NoteEntity> findMaxNoteIdByCreatedBy(String createdBy);
}
