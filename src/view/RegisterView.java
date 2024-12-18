package view;

import model.User;

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
        String name = scanner.nextLine();
        if (name.length() < 10)
            throw new IllegalArgumentException("Please enter your full name.");
        return name;
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
        int age = scanner.nextInt();
        scanner.nextLine();
        if (age < 18) {
            throw new IllegalArgumentException("Age cannot be minor than 18.");
        }
        return age;
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

    public void printPersonData(User<Object> user, int index) {
        System.out.println((index + 1) + ". " + user.field().get(0).toString());
    }

    public String searchName() {
        System.out.println("Enter the name or e-mail to search: ");
        return scanner.nextLine();
    }
}
