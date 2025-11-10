package dev.ddanylenko.noteapp.note.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatWrapper {
    private String title;

    private Map<String, Integer> map;

}
