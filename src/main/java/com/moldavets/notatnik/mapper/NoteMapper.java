package com.moldavets.notatnik.mapper;

import com.moldavets.notatnik.model.Note;
import com.moldavets.notatnik.model.entity.NoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NoteMapper {

    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    @Mapping(source = "noteId", target = "id")
    Note map(NoteEntity note);

    @Mapping(source = "id", target = "noteId")
    NoteEntity map(Note note);

    @Mapping(source = "noteId", target = "id")
    List<Note> map(List<NoteEntity> noteEntities);

}
