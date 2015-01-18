package java8.model;

import java8.model.person.Person;
import java8.model.transaction.City;
import java8.model.transaction.Traders;
import java8.model.transaction.Transaction;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.*;
import static java8.model.transaction.Transaction.TransactionTime;
import static java8.model.transaction.Transaction.TransactionValue;

public class TransactionCollectorsMain {


    public static void main(String[] args) {

        Person p1 = new Person.PersonBuilder("Susan", "Alan").city(City.of("Krakow")).build();
        Person p2 = new Person.PersonBuilder("Mike", "Alan").city(City.of("Warsaw")).build();
        Person p3 = new Person.PersonBuilder("Norris", "Kompi").city(City.of("Wroclaw")).build();
        Person p4 = new Person.PersonBuilder("Chuck", "Bees").city(City.of("Krakow")).build();
        Person p5 = new Person.PersonBuilder("Lee", "Bee").city(City.of("Warsaw")).build();

        Transaction t1 = futureTransaction("100", p1, p2, p3);
        Transaction t11 = futureTransaction("400", p1, p2, p3);
        Transaction t2 = futureTransaction("1000", p4, p2);
        Transaction t3 = futureTransaction("2100", p1, p3);
        Transaction t4 = historyTransaction("250", p1, p2);
        Transaction t5 = historyTransaction("150", p4, p5);

        List<Transaction> transactions = Arrays.asList(t1, t11, t2, t3, t4, t5);

        Comparator<Transaction> byValueComparator = (x1, x2) -> x1.getValue().compareTo(x2.getValue());

        Optional<Transaction> max = transactions.stream().max(byValueComparator);
        Optional<Transaction> max2 = transactions.stream().collect(maxBy(byValueComparator));
        assert max == max2;
        System.out.println(max);
        System.out.println(max2);

        IntSummaryStatistics summaryStatistics = transactions.stream().collect(summarizingInt(value -> value.getValue().intValue()));
        Optional<BigInteger> valueSum = transactions.stream().map(Transaction::getValue).reduce((x1, x2) -> x1.add(x2));
        assert valueSum.get().intValue() == summaryStatistics.getSum();

        String joinedIds = transactions.stream().map(Transaction::getId).collect(joining(", "));
        System.out.println(joinedIds);

        Optional<Transaction> transactionWithMaximumValue = transactions.stream().collect(reducing((Transaction transaction, Transaction transaction2) -> transaction.getValue().compareTo(transaction2.getValue()) > 0 ? transaction : transaction2));
        System.out.println(transactionWithMaximumValue);
        assert transactionWithMaximumValue.get().getValue().intValue() == summaryStatistics.getMax();

        // Difference between reduction and collecting - reduction is meant to be immutable - simply take 2 values and return a new one.
        // Anc reduction with mutability is a wrong thing for parallelism

        Map<TransactionTime, Map<TransactionValue, List<Transaction>>> transactionMultiGroup = transactions.stream().collect(groupingBy(Transaction::getTransactionTime, groupingBy(Transaction::getTransactionValue)));
        System.out.println(transactionMultiGroup);

        Map<TransactionTime, Long> transactionTimesCount = transactions.stream().collect(groupingBy(Transaction::getTransactionTime, counting()));
        System.out.println(transactionTimesCount);

        Map<TransactionTime, Transaction> maxValuePerTime = transactions.stream().collect(groupingBy(Transaction::getTransactionTime,
                collectingAndThen(
                        maxBy(Comparator.comparingInt((Transaction x) -> x.getValue().intValue())),
                        Optional::get)));
        System.out.println(maxValuePerTime);

    }

    private static Transaction futureTransaction(String value, Person... persons) {
        return new Transaction(new BigInteger(value), LocalDateTime.now().plusDays(randomNumberOfDays()), new Traders(persons));
    }

    private static Transaction historyTransaction(String value, Person... persons) {
        return new Transaction(new BigInteger(value), LocalDateTime.now().minusDays(randomNumberOfDays()), new Traders(persons));
    }

    private static long randomNumberOfDays() {
        return new Random().nextInt(10) + 1L;
    }

}
