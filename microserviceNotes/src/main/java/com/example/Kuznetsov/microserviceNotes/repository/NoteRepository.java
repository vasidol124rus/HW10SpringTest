package com.example.Kuznetsov.microserviceNotes.repository;

import com.example.Kuznetsov.microserviceNotes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}