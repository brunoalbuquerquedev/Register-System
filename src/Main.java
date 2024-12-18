import controller.Menu;
import controller.PersonController;
import repository.FileManager;
import view.RegisterView;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        FileManager manager = new FileManager();
        File file = new File(manager.getFilenamePath());

         manager.initiateSystemRepos();

        PersonController controller = new PersonController();
        Menu menu = new Menu(controller, manager, manager.getFields());
        RegisterView register = new RegisterView(manager.getFields());
    }
}
