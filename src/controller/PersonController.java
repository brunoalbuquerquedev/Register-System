package controller;

import model.User;
import repository.FileManager;
import view.RegisterView;

import java.util.ArrayList;
import java.util.TreeMap;

public class PersonController {

    public PersonController() {

    }

    public User<Object> registerNewPerson(RegisterView register) {
        try {
            User<Object> p = new User<>(new TreeMap<>());
            p.field().put(0, register.requestName());
            p.field().put(1, register.requestEmail());
            p.field().put(2, register.requestAge());
            p.field().put(3, register.requestHeight());

            String l;
            int index = 4;

            while ((l = register.requestField(index)) != null) {
                p.field().put((index), l);
                index++;
            }
            return p;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void loadUserData(FileManager manager, RegisterView register) {
        ArrayList<User<Object>> users = manager.getData();
        int index = 0;

        if (users.isEmpty())
            return;

        for (User<Object> obj : users) {
            register.printPersonData(obj, index);
            index++;
        }
    }
}