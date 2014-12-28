package java8.stream;

import java8.stream.model.Person;
import java8.stream.model.Sex;
import java8.stream.model.stringify.PersonStringifier;
import java8.stream.model.stringify.SurnameSexStrigifier;
import java8.stream.predicates.FemaleWithSurnameOnW;
import java8.stream.predicates.PersonPredicate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Stream {


    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person("Adam", "Bajer"), new Person("Adam", "Bajerek"),
                new Person("Tomasz", "Super"), new Person("Jadzia", "Wokosinska", Sex.FEMALE));
        Map<String, List<Person>> personsByName = persons.stream().collect(Collectors.groupingBy(Person::getName));
        personsByName.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " : " + x.getValue().stream().map(Person::getSurname).collect(Collectors.joining(", "))));

        List<Person> filteredPersons = filter(persons, new FemaleWithSurnameOnW());
        
        printPersons(filteredPersons, new SurnameSexStrigifier());
    }

    private static void printPersons(List<Person> persons, PersonStringifier personStringifier) {
        persons.stream().forEach(x -> System.out.println(personStringifier.toString(x)));
    }

    private static List<Person> filter(List<Person> persons, PersonPredicate personPredicate) {
        return persons.stream().filter(personPredicate).collect(toList());
    }
}
