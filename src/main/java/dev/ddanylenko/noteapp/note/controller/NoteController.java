package dev.ddanylenko.noteapp.note.controller;

import dev.ddanylenko.noteapp.note.model.NoteEntity;
import dev.ddanylenko.noteapp.note.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
