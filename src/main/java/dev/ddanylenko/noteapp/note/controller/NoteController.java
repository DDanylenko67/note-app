package dev.ddanylenko.noteapp.note.controller;

import dev.ddanylenko.noteapp.note.model.NoteDto;
import dev.ddanylenko.noteapp.note.model.NoteEntity;
import dev.ddanylenko.noteapp.note.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{id})")
    public ResponseEntity<NoteEntity> getNoteById(@PathVariable("id") Long id) {
        logger.info("Getting note by id={}", id);
        NoteEntity noteEntity = noteService.getNoteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(noteEntity);
    }

    @PostMapping
    public ResponseEntity<NoteEntity> createNote(@RequestBody NoteDto noteDto) {
        logger.info("Creating new note note={}", noteDto);
        NoteEntity createdEntity = noteService.createNote(noteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEntity);
    }

    @PostMapping("/{id}")
    public ResponseEntity<NoteEntity> updateNote(@PathVariable("id") Long id, @RequestBody NoteDto noteDto) {
        logger.info("Updating note with id={}", id);
        NoteEntity updatedEntity = noteService.updateNote(id, noteDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NoteEntity> deleteNote(@PathVariable("id") Long id) {
        logger.info("Deleting note with id={}", id);
        noteService.deleteNote(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
