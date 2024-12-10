package model;

public record Person(String name, String email, int age, float height) {

    @Override
    public String toString() {
        return String.format("Name: %s, E-mail: %s, Age: %d, Height: %f", name, email, age, height);
    }
}
