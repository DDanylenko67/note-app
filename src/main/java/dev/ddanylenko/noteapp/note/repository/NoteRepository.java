package dev.ddanylenko.noteapp.note.repository;

import dev.ddanylenko.noteapp.note.model.NoteEntity;
import dev.ddanylenko.noteapp.note.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<NoteEntity, String> {
    Page<NoteEntity> findByTags(Tag[] tags, Pageable pageable);
}
