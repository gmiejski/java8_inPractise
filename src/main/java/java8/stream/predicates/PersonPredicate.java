package java8.stream.predicates;

import java8.model.Person;

import java.util.function.Predicate;

public interface PersonPredicate extends Predicate<Person> {
    public boolean test(Person person);
}
