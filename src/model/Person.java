package model;

public class Person {

    private String name;
    private String email;
    private int age;
    private long height;

    public Person (String name, String email, int age, long height) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public long getHeight() {
        return height;
    }
}
