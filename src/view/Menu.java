package view;

import java.util.Scanner;

public class Menu {

    public Menu() {
        menu();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the operation: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    /* Register an user. */
                    System.out.print("Register: ");
                    break;

                case 2:
                    /* Output a list of all users. */
                    System.out.print("");
                    break;

                case 3:
                    /* Register a new quest on the form. */
                    System.out.print("");
                    break;

                case 4:
                    /* Delete a quest from the form. */
                    System.out.print("");
                    break;

                case 5:
                    /* Search users. */
                    System.out.print("");
                    break;

                case 6:
                    /* End the form. */
                    System.out.print("Ending the system. ");
                    scanner.close();
                    return;

                default:
                    break;
            }
        }
    }
}
