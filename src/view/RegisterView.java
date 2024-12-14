package view;

import model.Person;

import java.util.Scanner;
import java.util.TreeMap;

public class RegisterView {

    private final TreeMap<Integer, String[]> fields;
    private final Scanner scanner = new Scanner(System.in);

    public RegisterView(TreeMap<Integer, String[]> fields) {
        this.fields = fields;
    }

    public String requestNewField() {
        System.out.print("Enter the new field name: ");
        String s = scanner.nextLine();
        if (s == null) {
            throw new IllegalArgumentException("Field cannot be null.");
        }
        return s;
    }

    public String requestField(int index) {
        if (fields.get(index) == null) {
            return null;
        }
        System.out.printf(String.join(" ", fields.get(index)) + " ");
        return scanner.nextLine();
    }

    public String requestName() {
        System.out.printf(String.join(" ", fields.get(0)) + " ");
        return scanner.nextLine();
    }

    public String requestEmail() {
        System.out.printf(String.join(" ", fields.get(1)) + " ");
        String s = scanner.nextLine();
        char target = '@';
        if (s.chars().noneMatch(c -> c == target)) {
            throw new IllegalArgumentException("E-mail should have a '@'.");
        }
        return s;
    }

    public Integer requestAge() {
        System.out.printf(String.join(" ", fields.get(2)) + " ");
        int a = scanner.nextInt();
        scanner.nextLine();
        if (a < 18) {
            throw new IllegalArgumentException("Age cannot be minor than 18.");
        }
        return a;
    }

    public Float requestHeight() {
        System.out.printf(String.join(" ", fields.get(3)) + " ");
        float f = scanner.nextFloat();
        scanner.nextLine();
        if (f < 1.01) {
            throw new IllegalArgumentException();
        }
        return f;
    }

    public void printPersonData(Person<Object> person, int index) {
        System.out.println("Person " + (index + 1));
        System.out.println(person.toString());
    }
}
