package view;

import java.util.Scanner;
import java.util.TreeMap;

public class RegisterView {

    private TreeMap<Integer, String> fields;
    Scanner scanner = new Scanner(System.in);

    public RegisterView() {
    }

    public String requestField(int index) {
        System.out.printf("Enter your %s: ", fields.get(index));
        return scanner.nextLine();
    }
}
