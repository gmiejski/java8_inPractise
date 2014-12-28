package java8.stream.model.stringify;

import java8.stream.model.Person;

public class SurnameSexStrigifier  implements PersonStringifier{

    @Override
    public String toString(Person person) {
        return person.getSex().toString();
    }
}
