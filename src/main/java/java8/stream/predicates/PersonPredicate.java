package java8.stream.predicates;

import java8.stream.collection.model.person.Person;

import java.util.function.Predicate;

public interface PersonPredicate extends Predicate<Person> {
    public boolean test(Person person);
}
