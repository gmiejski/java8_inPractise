package java8.stream.collection.model.person.stringify;

import java8.stream.collection.model.person.Person;

public class SurnameSexStrigifier implements PersonStringifier {

    @Override
    public String toString(Person person) {
        return person.getSex().toString();
    }
}
