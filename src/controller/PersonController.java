package controller;

import model.Person;
import repository.FileManager;
import view.RegisterView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class PersonController {

    public PersonController() {

    }

    public Person<Object> registerNewPerson(RegisterView register) {
        Person<java.lang.Object> p = new Person<>(new TreeMap<>());
        try {
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
        } catch (RuntimeException e) {
            return null;
        }
        return p;
    }

    public void loadPersonData(FileManager manager, RegisterView register) throws IOException {
        ArrayList<Person<Object>> people = manager.getData(manager);
        int index = 0;

        for (Person<Object> obj : people) {
            register.printPersonData(obj, index);
            index++;
        }
    }
}