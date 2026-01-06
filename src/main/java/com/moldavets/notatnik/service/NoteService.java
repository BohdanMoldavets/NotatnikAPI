package com.moldavets.notatnik.service;

import com.moldavets.notatnik.mapper.NoteMapper;
import com.moldavets.notatnik.model.Note;
import com.moldavets.notatnik.repository.NoteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.moldavets.notatnik.utils.SecurityUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> getAll() {
        var username = extractUsername(getCurrentUser());
        var userNotes = noteRepository.getAllByCreatedBy(username);
        return NoteMapper.INSTANCE.map(userNotes);
    }

    public Note getById(Long id) {
        var username = extractUsername(getCurrentUser());
        var userNote = noteRepository.getByNoteIdAndCreatedBy(id, username)
                .orElseThrow( () -> new EntityNotFoundException("Note not found"));
        return NoteMapper.INSTANCE.map(userNote);
    }

    public Note save(Note note) {
        var username = extractUsername(getCurrentUser());
        var noteEntity = NoteMapper.INSTANCE.map(note);
        noteEntity.setCreatedBy(username);
        noteEntity.setNoteId(determineNextNoteId(username));
        var storedEntity = noteRepository.saveAndFlush(noteEntity);
        return NoteMapper.INSTANCE.map(storedEntity);
    }

    public Note update(Long id, Note note) {
        var username = extractUsername(getCurrentUser());
        var storedEntity = noteRepository.getByNoteIdAndCreatedBy(id, username)
                .orElseThrow( () -> new EntityNotFoundException("Note not found"));
        storedEntity.setContent(note.getContent());
        return NoteMapper.INSTANCE.map(noteRepository.saveAndFlush(storedEntity));
    }

    public void delete(Long id) {
        var username = extractUsername(getCurrentUser());
        var storedEntity = noteRepository.getByNoteIdAndCreatedBy(id, username)
                .orElseThrow( () -> new EntityNotFoundException("Note not found"));
        noteRepository.delete(storedEntity);
    }

    private String extractUsername(User user) {
        return user.getUsername();
    }

    private long determineNextNoteId(String username) {
        var entityWithMaxNoteId = noteRepository.findMaxNoteIdByCreatedBy(username)
                .orElse(null);
        return Objects.nonNull(entityWithMaxNoteId) ? entityWithMaxNoteId.getNoteId() + 1 : 0;
    }

}
