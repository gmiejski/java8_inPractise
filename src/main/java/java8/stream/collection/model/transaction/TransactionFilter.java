package java8.stream.collection.model.transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class TransactionFilter {

    public static List<Transaction> beforeDate(List<Transaction> transactions, LocalDateTime date) {
        return transactions.stream().filter(t -> t.getDate().isBefore(date)).collect(toList());
    }

    public static <R extends Comparable> List<Transaction> sorted(List<Transaction> transactions, Function<Transaction, R> function) {
        List<Transaction> sorted = new ArrayList<>(transactions);
        transactions.sort(Comparator.comparing(function));
        return sorted;
    }

}
