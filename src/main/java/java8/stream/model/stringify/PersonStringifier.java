package java8.stream.model.stringify;

import java8.stream.model.Person;

@FunctionalInterface
public interface PersonStringifier {

    String toString(Person person);

}
