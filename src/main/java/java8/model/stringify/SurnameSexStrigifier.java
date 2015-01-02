package java8.model.stringify;

import java8.model.Person;

public class SurnameSexStrigifier  implements PersonStringifier{

    @Override
    public String toString(Person person) {
        return person.getSex().toString();
    }
}
