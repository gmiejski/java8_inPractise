package java8.stream;

import java8.stream.collection.model.person.Person;
import java8.stream.collection.model.person.stringify.PersonStringifier;
import java8.stream.predicates.FemaleWithSurnameOnW;
import java8.stream.predicates.PersonPredicate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Stream {


    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person.PersonBuilder("Adam", "Bajer").build(), new Person.PersonBuilder("Adam", "Bajerek").build(),
                new Person.PersonBuilder("Tomasz", "Super").build(), new Person.PersonBuilder("Jadzia", "Wokosinska").build());
        Map<String, List<Person>> personsByName = persons.stream().collect(Collectors.groupingBy(Person::getName));
        personsByName.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " : " + x.getValue().stream().map(Person::getSurname).collect(Collectors.joining(", "))));

        persons.sort(Comparator.comparing(Person::getName));
        printPersons(persons, Person::toString);

        List<Person> filteredPersons = filter(persons, new FemaleWithSurnameOnW());
        printPersons(filteredPersons, x -> x.getSex().toString());
    }

    private static void printPersons(List<Person> persons, PersonStringifier personStringifier) {
        persons.stream().forEach(x -> System.out.println(personStringifier.toString(x)));
    }

    private static List<Person> filter(List<Person> persons, PersonPredicate personPredicate) {
        return persons.stream().filter(personPredicate).collect(toList());
    }
}
