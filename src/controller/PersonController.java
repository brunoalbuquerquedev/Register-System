package controller;

import model.Person;
import view.RegisterView;

import java.util.ArrayList;

public class PersonController {

    private ArrayList<String> fields;

    public PersonController() {
    }

    public Person registerNewPerson() {
        RegisterView register = new RegisterView();

        for (int a = 0; a < fields.size(); a++) {
            fields.add(register.requestField(a));
        }

        String name = fields.getFirst();
        String email = fields.get(1);
        int age = Integer.parseInt(fields.get(2));
        long height = Integer.parseInt(fields.get(3));

        return new Person(name, email, age, height);
    }
}
