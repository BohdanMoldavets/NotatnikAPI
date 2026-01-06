package com.moldavets.notatnik.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NOTES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "NOTE_ID")
    public Long noteId;

    @Column(name = "CONTENT", nullable = false)
    public String content;

    @Column(name = "CREATED_BY")
    public String createdBy;

}
