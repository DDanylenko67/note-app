package dev.ddanylenko.noteapp.note.repository;

import dev.ddanylenko.noteapp.note.model.NoteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<NoteEntity, Long> {

}
