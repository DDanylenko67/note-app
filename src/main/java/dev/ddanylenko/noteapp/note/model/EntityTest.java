package dev.ddanylenko.noteapp.note.model;

public class EntityTest {

    private String one;
    private String two;

    public EntityTest(String one, String two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public String toString() {
        return "EntityTest{" +
                "one='" + one + '\'' +
                ", two='" + two + '\'' +
                '}';
    }
}
