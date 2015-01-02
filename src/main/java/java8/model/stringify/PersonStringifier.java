package java8.model.stringify;

import java8.model.Person;

@FunctionalInterface
public interface PersonStringifier {

    String toString(Person person);

}
