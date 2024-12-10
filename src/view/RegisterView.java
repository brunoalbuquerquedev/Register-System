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
        System.out.printf("Enter your %s: ", String.join(" ", fields.get(index)));
        return scanner.nextLine();
    }

    public int requestIntField(int index) {
        System.out.printf("Enter your %s: ", String.join(" ", fields.get(index)));
        return scanner.nextInt();
    }

    public float requestLongField(int index) {
        System.out.printf("Enter your %s: ", String.join(" ", fields.get(index)));
        return scanner.nextFloat();
    }
}
