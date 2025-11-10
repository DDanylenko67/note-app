package dev.ddanylenko.noteapp.config.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(String id) {
        super("Note not found with id: " + id);
    }
}