package java8.stream.predicates;

import java8.model.Person;
import java8.model.Sex;

public class FemaleWithSurnameOnW implements PersonPredicate {

    public static final String STARTING_CHARACTER = "W";

    @Override
    public boolean test(Person person) {
        return person.getSurname().startsWith(STARTING_CHARACTER) && Sex.FEMALE.equals(person.getSex());
    }
}
