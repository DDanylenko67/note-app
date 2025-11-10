package dev.ddanylenko.noteapp.note.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteShortDto {
    private String id;

    private String title;

    private LocalDateTime createdAt;
}
