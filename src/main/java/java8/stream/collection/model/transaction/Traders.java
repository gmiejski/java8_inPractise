package java8.stream.collection.model.transaction;

import java8.stream.collection.model.person.Person;

import java.util.Arrays;
import java.util.Collection;

public class Traders {

    private Collection<Person> persons;

    public Traders(Collection<Person> persons) {
        this.persons = persons;
    }

    public Traders(Person... persons) {
        this.persons = Arrays.asList(persons);
    }

    public Collection<Person> getPersons() {
        return persons;
    }
}
