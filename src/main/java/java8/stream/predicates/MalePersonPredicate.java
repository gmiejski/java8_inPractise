package java8.stream.predicates;

import java8.stream.collection.model.person.Person;
import java8.stream.collection.model.person.Sex;

public class MalePersonPredicate implements PersonPredicate {
    @Override
    public boolean test(Person person) {
        return Sex.FEMALE.equals(person.getSex());
    }
}
