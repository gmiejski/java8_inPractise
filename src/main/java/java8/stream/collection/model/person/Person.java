package java8.stream.collection.model.person;

import java8.stream.collection.model.transaction.City;

public class Person {

    private final String name;
    private final String surname;
    private Sex sex;
    private City city;

    private Person(String name, String surname, Sex sex, City city) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Sex getSex() {
        return sex;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "{" + name + " " + surname + " : " + sex.toString() + "}";
    }

    public static class PersonBuilder {

        private String name;
        private String surname;
        private Sex sex;
        private City city;

        public PersonBuilder(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public Person build() {
            return new Person(name, surname, sex, city);
        }

        public PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public PersonBuilder sex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public PersonBuilder city(City city) {
            this.city = city;
            return this;
        }
    }

}

