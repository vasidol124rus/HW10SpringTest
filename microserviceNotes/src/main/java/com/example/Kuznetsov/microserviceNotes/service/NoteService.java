package com.example.Kuznetsov.microserviceNotes.service;

import com.example.Kuznetsov.microserviceNotes.models.Note;

import java.util.List;

public interface NoteService {

    Note createNote(Note note);

    List<Note> getAllNotes();

    Note getNoteById(Long id);

    Note updateNote(Note note);

    void deleteNote(Long id);

}
