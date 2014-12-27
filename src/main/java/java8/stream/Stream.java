package java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream {

    private static class Person {

        private final String name;
        private final String surname;

        public Person(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }
    }

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person("Adam", "Bajer"), new Person("Adam", "Bajerek"), new Person("Tomasz", "Super"));
        Map<String, List<Person>> personsByName = persons.stream().collect(Collectors.groupingBy(Person::getName));
        personsByName.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " : " + x.getValue().stream().map(Person::getSurname).collect(Collectors.joining(", "))));
    }

}
