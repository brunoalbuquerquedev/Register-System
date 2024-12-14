package controller;

import model.Person;
import repository.FileManager;
import view.RegisterView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Menu {

    private final PersonController controller;
    private final FileManager manager;
    private final RegisterView register;
    private final TreeMap<Integer, String[]> fields;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(PersonController controller, FileManager manager, TreeMap<Integer, String[]> fields) {
        this.controller = controller;
        this.manager = manager;
        this.fields = fields;
        this.register = new RegisterView(fields);
        menu();
    }

    public void menu() {
        while (true) {
            showOptions();
            int option = getUserOption();

            try {
                executeOption(option);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            if (option == 6) {
                System.out.println("Ending the system.");
                break;
            }
        }
        scanner.close();
    }

    private void showOptions() {
        System.out.println("\n1. Register a user");
        System.out.println("2. List all users");
        System.out.println("3. Add a new field");
        System.out.println("4. Delete a field");
        System.out.println("5. Search users");
        System.out.println("6. Exit");
        System.out.print("Enter the operation: ");
    }

    private int getUserOption() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid option. Please enter a number.");
            scanner.next();
        }
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private void executeOption(int option) throws IOException {
        switch (option) {
            case 1 -> registerUser();
            case 2 -> listUsers();
            case 3 -> addField();
            case 4 -> deleteField();
            case 5 -> searchUsers();
            case 6 -> System.out.println("Ending program.");
            default -> System.out.println("Invalid option.");
        }
    }

    private void registerUser() throws IOException {
        System.out.println("Registering a user...");
        Person<Object> person = controller.registerNewPerson(manager, register);
        manager.createNewFile(person);
        System.out.println();
        System.out.println();
    }

    private void listUsers() throws IOException {
        System.out.println("Listing all users...");
        controller.loadPersonData(manager, register);
        System.out.println();
    }

    private void addField() {
        String newField = register.requestNewField();
        try {
            manager.addField(newField);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return;
        }
        System.out.println("Field added successfully.");
        System.out.println();
    }

    private void deleteField() throws IOException {
        System.out.print("Enter the field key to delete: ");
        int key = scanner.nextInt();
        manager.deleteField(key);
        System.out.println("Person deleted successfully.");
    }

    private void searchUsers() {
        System.out.println("Searching for users...");
        // Logic for searching users
    }
}
