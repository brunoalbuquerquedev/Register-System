import controller.Menu;
import controller.PersonController;
import repository.FileManager;
import view.RegisterView;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        try {
            FileManager manager = new FileManager();
            PersonController controller = new PersonController();
            Menu menu = new Menu(controller, manager, manager.getFields());
            RegisterView register = new RegisterView(manager.getFields());
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}