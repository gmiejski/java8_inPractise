package java8.model;

import java8.model.person.Person;
import java8.model.transaction.City;
import java8.model.transaction.Traders;
import java8.model.transaction.Transaction;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TransactionSimpleExamplesMain {

    public static void main(String[] args) {

        Person p1 = new Person.PersonBuilder("Susan", "Alan").city(City.of("Krakow")).build();
        Person p2 = new Person.PersonBuilder("Mike", "Alan").city(City.of("Warsaw")).build();
        Person p3 = new Person.PersonBuilder("Norris", "Kompi").city(City.of("Wroclaw")).build();
        Person p4 = new Person.PersonBuilder("Chuck", "Bees").city(City.of("Krakow")).build();
        Person p5 = new Person.PersonBuilder("Lee", "Bee").city(City.of("Warsaw")).build();

        Transaction t1 = new Transaction(new BigInteger("100"), LocalDateTime.parse("2007-12-03T07:15:30"), new Traders(p1, p2, p3));
        Transaction t2 = new Transaction(new BigInteger("1000"), LocalDateTime.parse("2007-11-03T05:15:30"), new Traders(p4, p2));
        Transaction t3 = new Transaction(new BigInteger("2100"), LocalDateTime.parse("2007-12-21T17:15:30"), new Traders(p1, p3));
        Transaction t4 = new Transaction(new BigInteger("500"), LocalDateTime.parse("2007-12-22T15:15:30"), new Traders(p1, p2));
        Transaction t5 = new Transaction(new BigInteger("150"), LocalDateTime.parse("2007-11-26T12:15:30"), new Traders(p4, p5));

        List<Transaction> transactions = Arrays.asList(t1, t2, t3, t4, t5);

        List<Transaction> beforeDateOrderedByValue = beforeDateAndSorted(transactions, LocalDateTime.parse("2007-12-21T15:15:30"));
        beforeDateOrderedByValue.forEach(System.out::println);
        System.out.println();

        List<City> uniqueCities = transactions.stream().map(Transaction::getTraders).flatMap(x -> x.getPersons().stream()).map(Person::getCity).distinct().collect(toList());
        System.out.println(uniqueCities);

        List<String> tradersNamesSortedReverse = transactions.stream().map(Transaction::getTraders).flatMap(x -> x.getPersons().stream()).map(Person::getName).distinct().sorted(String::compareToIgnoreCase).sorted(Comparator.reverseOrder()).collect(toList());
        System.out.println(tradersNamesSortedReverse);

        List<BigInteger> wroclawTransactions = transactions.stream().filter(x -> x.getTraders().getPersons().stream().anyMatch(y -> y.getCity().equals(City.of("Wroclaw")))).map(Transaction::getValue).collect(toList());
        System.out.println(wroclawTransactions);

        Optional<BigInteger> max = transactions.stream().map(Transaction::getValue).max(BigInteger::compareTo);
        Optional<BigInteger> max2 = transactions.stream().map(Transaction::getValue).reduce(BigInteger::max);
        max.ifPresent(System.out::println);
        assert max.get().compareTo(max2.get()) == 0;

        Optional<Transaction> lowestValueTransaction = transactions.stream().min(Comparator.comparing(Transaction::getValue));
        lowestValueTransaction.ifPresent(System.out::println);

    }

    private static List<Transaction> beforeDateAndSorted(List<Transaction> transactions, LocalDateTime date) {
        return transactions.stream().filter(x -> x.getDate().isBefore(date)).sorted(comparing(Transaction::getValue)).collect(toList());
    }


}
