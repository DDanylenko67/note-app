package dev.ddanylenko.noteapp.note.model;

import dev.ddanylenko.noteapp.note.model.dto.NoteDto;
import dev.ddanylenko.noteapp.note.model.dto.NoteShortDto;
import dev.ddanylenko.noteapp.note.model.dto.NoteWithoutText;

public class NoteMapper {
    public static NoteEntity toNoteEntity(NoteDto dto) {
        return new NoteEntity(
                dto.getId(),
                dto.getTitle(),
                dto.getCreateDate(),
                dto.getText(),
                dto.getTags()
        );
    }
    public static NoteEntity toNoteDto(NoteEntity entity) {
        return new NoteEntity(
                entity.getId(),
                entity.getTitle(),
                entity.getCreateDate(),
                entity.getText(),
                entity.getTags()
        );
    }

    public static NoteShortDto toNoteShortDto(NoteEntity entity) {
        return new NoteShortDto(
                entity.getId(),
                entity.getTitle(),
                entity.getCreateDate()
        );
    }

    public static NoteWithoutText toNoteWithoutText(NoteEntity entity) {
        return new NoteWithoutText(
                entity.getId(),
                entity.getTitle(),
                entity.getCreateDate(),
                entity.getTags()
        );
    }
}
