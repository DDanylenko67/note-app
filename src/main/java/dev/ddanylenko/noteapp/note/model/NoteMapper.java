package dev.ddanylenko.noteapp.note.model;

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
}
