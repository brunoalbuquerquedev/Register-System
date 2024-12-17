package model;

import java.util.Map;

public record Person<T>(Map<Integer, T> field) {

    public Person(Map<Integer, T> field) {
        this.field = field;
    }

    public String fieldToString() {
        StringBuilder sb = new StringBuilder("Fields:\n");
        field.forEach((key, value) -> sb.append(value).append("\n"));
        return sb.toString().trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Person Details:\n");
        field.forEach((key, value) -> sb.append("Field ").append(key).append(": ").append(value).append("\n"));
        return sb.toString().trim();
    }
}
