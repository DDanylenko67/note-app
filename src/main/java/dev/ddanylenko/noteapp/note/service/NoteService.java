package dev.ddanylenko.noteapp.note.service;

import dev.ddanylenko.noteapp.config.exception.NoteNotFoundException;
import dev.ddanylenko.noteapp.note.model.NoteEntity;
import dev.ddanylenko.noteapp.note.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final Logger logger = LoggerFactory.getLogger(NoteService.class);

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public NoteEntity getNoteById(Long id) {
        logger.info("Getting not in NoteService with id={}", id);
        return findById(id);
    }

    public NoteEntity findById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
    }
}
