package java8.stream.predicates;

import java8.model.Person;
import java8.model.Sex;

public class MalePersonPredicate implements PersonPredicate{
    @Override
    public boolean test(Person person) {
        return Sex.FEMALE.equals(person.getSex());
    }
}
