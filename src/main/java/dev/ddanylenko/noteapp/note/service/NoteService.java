package dev.ddanylenko.noteapp.note.service;

import dev.ddanylenko.noteapp.config.exception.NoteNotFoundException;
import dev.ddanylenko.noteapp.note.model.NoteDto;
import dev.ddanylenko.noteapp.note.model.NoteEntity;
import dev.ddanylenko.noteapp.note.model.NoteMapper;
import dev.ddanylenko.noteapp.note.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public NoteEntity createNote(NoteDto noteDto) {
        logger.info("Creating new note in service");
        if(noteDto.getId() != null) {
            throw new IllegalArgumentException("Id should be null");
        }
        if(noteDto.getTitle() == null || noteDto.getText() == null) {
            throw new IllegalStateException("Text and title should not be null");
        }
        NoteEntity noteEntity = NoteMapper.toNoteEntity(noteDto);
        noteEntity.setCreateDate(LocalDateTime.now());
        return noteRepository.save(noteEntity);
    }

    public NoteEntity updateNote(Long id, NoteDto noteDto) {
        logger.info("Updating note in service");
        if(noteDto.getTitle() == null || noteDto.getText() == null) {
            throw new IllegalStateException("Text and title should not be null");
        }
        NoteEntity noteEntity = findById(id);
        noteEntity.setTitle(noteDto.getTitle());
        noteEntity.setText(noteDto.getText());
        noteEntity.setTags(noteDto.getTags());
        return noteRepository.save(noteEntity);

    }

    public void deleteNote(Long id) {
        logger.info("Deleting note in service");
        findById(id);
        noteRepository.deleteById(id);
    }

    public NoteEntity findById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
    }

}
