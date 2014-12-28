package java8.stream.model;

public class Person {

    private final String name;
    private final String surname;
    private Sex sex;

    public Person(String name, String surname, Sex sex) {
        this(name, surname);
        this.sex = sex;
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.sex = Sex.MALE;
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

    @Override
    public String toString() {
        return "{" + name + " " + surname + " : " + sex.toString() + "}";
    }
}

