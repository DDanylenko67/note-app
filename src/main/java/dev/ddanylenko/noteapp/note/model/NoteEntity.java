package dev.ddanylenko.noteapp.note.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notes")
public class NoteEntity {
    @Id
    private Long id;

    @NotNull
    private String title;

    private LocalDateTime createDate;

    @NotNull
    private String text;

    private Tag[] tags;
}
