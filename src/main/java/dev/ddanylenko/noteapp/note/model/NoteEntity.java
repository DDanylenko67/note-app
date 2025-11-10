package dev.ddanylenko.noteapp.note.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notes")
public class NoteEntity {
    @Id
    private String id;

    @NotNull
    private String title;

    private LocalDateTime createDate;

    @NotNull
    private String text;

    private Tag[] tags;


    public Map<String, Integer> calcNoteStat(){
        Map<String, Integer> map = new HashMap<>();
        String[] strings = text.split(" ");
        for(String s : strings){
            if(map.containsKey(s)){
                map.put(s, map.get(s)+1);
            }
            else {
                map.put(s, 1);
            }
        }
        return map;
    }
}
