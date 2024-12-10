package controller;

import model.Person;
import view.RegisterView;

import java.util.TreeMap;

public class PersonController {

    public PersonController() {

    }

    public Person registerNewPerson(RegisterView register) {
        String name = register.requestStringField(0);
        String email = register.requestStringField(1);
        int age = register.requestIntField(2);
        float height = register.requestLongField(3);
        return new Person(name, email, age, height);
    }

    public Person loadPersonData() {
        return null;
    }
}
