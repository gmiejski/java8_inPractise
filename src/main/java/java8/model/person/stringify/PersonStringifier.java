package java8.model.person.stringify;

import java8.model.person.Person;

@FunctionalInterface
public interface PersonStringifier {

    String toString(Person person);

}
