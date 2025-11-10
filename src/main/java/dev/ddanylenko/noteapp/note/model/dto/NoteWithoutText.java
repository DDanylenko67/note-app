package dev.ddanylenko.noteapp.note.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.ddanylenko.noteapp.note.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteWithoutText {
    private String id;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createDate;

    private Tag[] tags;
}
