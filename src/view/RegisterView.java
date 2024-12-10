package view;

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
        return scanner.nextLine();
    }

    public String requestStringField(int index) {
        System.out.printf(String.join(" ", fields.get(index)));
        return scanner.nextLine();
    }

    public int requestIntField(int index) {
        System.out.printf(String.join(" ", fields.get(index)));
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    public float requestFloatField(int index) {
        System.out.printf(String.join(" ", fields.get(index)));
        float result = scanner.nextFloat();
        scanner.nextLine();
        return result;
    }

    public void printPersonData() {}

    public void printPersonData(String filepath) {

    }
}
