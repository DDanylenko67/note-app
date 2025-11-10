package dev.ddanylenko.noteapp.note.service;

import dev.ddanylenko.noteapp.config.exception.NoteNotFoundException;
import dev.ddanylenko.noteapp.note.model.*;
import dev.ddanylenko.noteapp.note.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final Logger logger = LoggerFactory.getLogger(NoteService.class);

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public NoteEntity getNoteById(String id) {
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

    public NoteEntity updateNote(String id, NoteDto noteDto) {
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

    public void deleteNote(String id) {
        logger.info("Deleting note in service");
        findById(id);
        noteRepository.deleteById(id);
    }

    public List<StatWrapper> getStats() {
        logger.info("Getting stats in service");
        List<NoteEntity> noteEntities = noteRepository.findAll();
        List<StatWrapper> stats = new ArrayList<>();
        for(NoteEntity noteEntity : noteEntities) {
            stats.add(new StatWrapper(noteEntity.getTitle(), noteEntity.calcNoteStat()));
        }
        return stats;
    }

    public NoteEntity findById(String id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
    }

    public Page<NoteEntity> findAll(Pageable pageable, String tags) {
        logger.info("Processing tags in service");
        Page<NoteEntity> page;
        List<Tag> tagList = new ArrayList<>();
        if(tags != null) {
            String[] tagToProcess = tags.split("\\s+");
            for(String tag : tagToProcess) {
                tag = tag.trim();
                try{
                    tagList.add(Tag.valueOf(tag));
                }catch (IllegalArgumentException ignored){

                }
            }
        }
        if(!tagList.isEmpty()){
            page = getAll(tagList.toArray(Tag[]::new), pageable);
        }
        else{
            page = getAll(pageable);
        }
        return page;
    }


    public Page<NoteEntity> getAll(Tag[] tagsArray, Pageable pageable) {
        logger.info("Getting all notes by tags={} and on page={}", Arrays.toString(tagsArray), pageable);
        return noteRepository.findByTags(tagsArray, pageable);
    }

    public Page<NoteEntity> getAll(Pageable pageable) {
        logger.info("Getting all notes ob page={}",pageable);
        return noteRepository.findAll(pageable);
    }
}
