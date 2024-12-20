package controller;

import model.User;
import repository.FileManager;
import view.RegisterView;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Menu {

    private final PersonController controller;
    private final FileManager manager;
    private final RegisterView register;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(PersonController controller, FileManager manager, TreeMap<Integer, String[]> fields) {
        this.controller = controller;
        this.manager = manager;
        this.register = new RegisterView(fields);
        menu();
    }

    public void menu() {
        while (true) {
            showOptions();
            int option = getUserOption();
            executeOption(option);

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

    private void executeOption(int option) {
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

    private void registerUser() {
        try {
            System.out.println("Registering a user...");
            User<Object> user = controller.registerNewPerson(register);
            manager.createNewFile(user);
            System.out.println("User registered successfully.");
        } catch (IOException | NullPointerException e) {
            System.out.println("Register user error." + Arrays.toString(e.getStackTrace()));
        }
    }

    private void listUsers() {
        try {
            System.out.println("Listing all users...");
            controller.loadUserData(manager, register);
            System.out.println();
        } catch (NullPointerException e) {
            System.out.println("Error when list users." + Arrays.toString(e.getStackTrace()));
        }
    }

    private void addField() {
        try {
            String newField = register.requestNewField();
            manager.addField(newField);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return;
        }
        System.out.println("Field added successfully.");
    }

    private void deleteField() {
        try {
            User<Object> p = manager.readFile(new File(manager.getFilenamePath()));
            System.out.println(p.fieldToString());
            System.out.print("Enter the field to delete (only the created fields): ");
            int key = scanner.nextInt();
            Integer num = key > 4 ? key : null;

            if (num != null) {
                manager.deleteField(num - 1);
                System.out.println("Person deleted successfully.");
            } else {
                System.out.println("Can't delete the default fields.");
            }
        } catch (NullPointerException e) {
            System.out.println("Delete field error. " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void searchUsers() {
        try {
            System.out.println("Searching for users...");
            String s = register.searchName();
            List<String> list = manager.userSearch(s);

            if (list.isEmpty()) {
                System.out.println("No data found.");
            } else if (s.chars().noneMatch(c -> c == '@')) {
                System.out.println("The user register was found in the system. The user name is " + list.getFirst() + ".");
            }
        } catch (NullPointerException e) {
            System.out.println("Search user error. " + Arrays.toString(e.getStackTrace()));
        }
    }
}
