package controller;

import model.Person;
import view.RegisterView;

public class PersonController {

    public PersonController() {

    }

    public Person registerNewPerson(RegisterView register) {
        String name = register.requestStringField(0);
        String email = register.requestStringField(1);
        int age = register.requestIntField(2);
        float height = register.requestFloatField(3);
        return new Person(name, email, age, height);
    }

    public Person loadPersonData(RegisterView register) {
        register.printPersonData();
        return null;
    }
}
