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

    @SuppressWarnings("unchecked")
    public <T> Person<T> registerNewPerson(FileManager manager, RegisterView register) {
        Person<T> p = new Person<>(new TreeMap<>());
        try {
            p.field().put(0, (T) register.requestName());
            p.field().put(1, (T) register.requestEmail());
            p.field().put(2, (T) register.requestAge());
            p.field().put(3, (T) register.requestHeight());

            String l;
            int index = 4;

            while ((l = register.requestField(index)) != null) {
                p.field().put((index), (T) l);
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