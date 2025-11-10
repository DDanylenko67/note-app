package dev.ddanylenko.noteapp.note.controller;

import dev.ddanylenko.noteapp.note.model.*;
import dev.ddanylenko.noteapp.note.model.dto.NoteDto;
import dev.ddanylenko.noteapp.note.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteEntity> getNoteById(@PathVariable("id") String id) {
        logger.info("Getting note by id={}", id);
        NoteEntity noteEntity = noteService.getNoteById(id);
        logger.info("Founded note by node={}", noteEntity);
        return ResponseEntity.status(HttpStatus.OK).body(noteEntity);
    }

    @PostMapping
    public ResponseEntity<NoteEntity> createNote(@RequestBody NoteDto noteDto) {
        logger.info("Creating new note={}", noteDto);
        NoteEntity createdEntity = noteService.createNote(noteDto);
        logger.info("Created note={}", createdEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEntity);
    }

    @PostMapping("/{id}")
    public ResponseEntity<NoteEntity> updateNote(@PathVariable("id") String id, @RequestBody NoteDto noteDto) {
        logger.info("Updating note={} with id={}",noteDto, id);
        NoteEntity updatedEntity = noteService.updateNote(id, noteDto);
        logger.info("Updated note={}",noteDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NoteEntity> deleteNote(@PathVariable("id") String id) {
        logger.info("Deleting note with id={}", id);
        noteService.deleteNote(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/stats")
    public ResponseEntity<List<StatWrapper>> getNoteStats() {
        logger.info("Getting stats of notes");
        List<StatWrapper> stats = noteService.getStats();
        return ResponseEntity.status(HttpStatus.OK).body(stats);
    }

    @GetMapping
    public ResponseEntity<Page<?>> getAllNotes(
            @RequestParam(required = false) String tags,
            @RequestParam(required = false, defaultValue = "0") int pages,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "false") boolean shortValue,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy
    ) {
        logger.info("Getting all notes with params: pages={}, size={}, tags={}, sort by ={}", pages, size, tags, sortBy);
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy).descending());
        Page<NoteEntity> page = noteService.findAll(pageable, tags);
        Page<?> resultPage = shortValue
                ? page.map(NoteMapper::toNoteShortDto)
                : page.map(NoteMapper::toNoteWithoutText);
        return ResponseEntity.status(HttpStatus.OK).body(resultPage);
    }

    @GetMapping("/text/{id}")
    public ResponseEntity<String> getNoteText(@PathVariable("id") String id) {
        logger.info("Getting text with note id={}", id);
        NoteEntity noteEntity = noteService.getNoteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(noteEntity.getText());
    }
}
