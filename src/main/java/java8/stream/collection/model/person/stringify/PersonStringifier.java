package java8.stream.collection.model.person.stringify;

import java8.stream.collection.model.person.Person;

@FunctionalInterface
public interface PersonStringifier {

    String toString(Person person);

}
