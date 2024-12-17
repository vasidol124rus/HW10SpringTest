package com.example.Kuznetsov.microserviceNotes;

import com.example.Kuznetsov.microserviceNotes.models.Note;
import com.example.Kuznetsov.microserviceNotes.repository.NoteRepository;
import com.example.Kuznetsov.microserviceNotes.service.impl.NoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

// Юнит-тесты
@ExtendWith(MockitoExtension.class)
public class NotesServiceUnitTests {

    // Создание mock для изоляции репозитория
    @Mock
    private NoteRepository noteRepository;

    // Создание тестируемого сервиса
    @InjectMocks
    private NoteServiceImpl noteServiceImpl;

    // Создание заметки для использования в тестах
    private Note note;

    // Инициализация заметки перед каждым тестом
    @BeforeEach
    public void setup() {

        note = new Note();
        note.setId(1L);
        note.setTitle("Тестовая заметка");
        note.setContents("Содержимое тестовой заметки");
        note.setCreationDate(LocalDateTime.now());
    }

    // Тестирование создания заметки
    @Test
    public void createNoteTest() {

        given(noteRepository.save(note)).willReturn(note); // Настройка mock

        assertEquals(note, noteServiceImpl.createNote(note));

        verify(noteRepository, times(1)).save(note);

    }

    // Тестирование получения всех заметок
    @Test
    public void getAllNotesTest() {

        // Создание списка заметок для использования в mock
        List<Note> notes = new ArrayList<>();

        // Создание второй заметки для добавления в список заметок
        Note note2 = new Note();
        note2.setId(2L);
        note2.setTitle("Тестовая заметка 2");
        note2.setContents("Содержимое тестовой заметки 2");
        note2.setCreationDate(LocalDateTime.now());

        // Добавление заметок в список
        notes.add(note);
        notes.add(note2);

        given(noteRepository.findAll()).willReturn(notes); // Настройка mock

        assertEquals(notes, noteServiceImpl.getAllNotes());

        verify(noteRepository, times(1)).findAll();

    }

    // Тестирование получения заметки по ID
    @Test
    public void getNoteByIdTest() {

        given(noteRepository.findById(note.getId())).willReturn(Optional.of(note)); // Настройка mock

        assertEquals(note, noteServiceImpl.getNoteById(1L));

        verify(noteRepository, times(1)).findById(note.getId());

    }

    // Тестирование обновления заметки
    @Test
    public void updateNoteTest() {

        // Создание обновленной заметки
        Note updatedNote = new Note();
        updatedNote.setId(1L);
        updatedNote.setTitle("Обновленная тестовая заметка");
        updatedNote.setContents("Содержимое обновленной тестовой заметки");
        updatedNote.setCreationDate(LocalDateTime.now());

        given(noteRepository.findById(updatedNote.getId())).willReturn(Optional.of(note)); // Mock для метода findById
        given(noteRepository.save(any(Note.class))).willReturn(updatedNote); // Mock для метода save

        assertEquals(updatedNote, noteServiceImpl.updateNote(updatedNote));

        verify(noteRepository, times(1)).findById(updatedNote.getId());
        verify(noteRepository, times(1)).save(any(Note.class));

    }

    // Тестирование удаления заметки
    @Test
    public void deleteNoteTest() {

        given(noteRepository.findById(note.getId())).willReturn(Optional.of(note)); // Mock для метода findById
        doNothing().when(noteRepository).delete(note); // Mock для метода delete

        noteServiceImpl.deleteNote(1L);

        verify(noteRepository, times(1)).findById(note.getId());
        verify(noteRepository, times(1)).delete(note);

    }

}