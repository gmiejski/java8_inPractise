package java8.stream.collection.model.transaction;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Transaction {

    private static int ID = 1;

    private final int id;
    private final BigInteger value;
    private final LocalDateTime date;
    private final Traders traders;

    public Transaction(BigInteger value, LocalDateTime date, Traders traders) {
        this.id = ID++;
        this.value = value;
        this.date = date;
        this.traders = traders;
    }

    public BigInteger getValue() {
        return value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Traders getTraders() {
        return traders;
    }

    @Override
    public String toString() {
        return "{" + "value=" + value + ", date=" + date + ", traders=" + traders + '}';
    }

    public String getId() {
        return String.valueOf(id);
    }

    public TransactionTime getTransactionTime() {
        return TransactionTime.byDate(this.getDate());
    }

    public TransactionValue getTransactionValue() {
        return TransactionValue.byValue(this.getValue());
    }

    public enum TransactionValue {
        LOW, MEDIUM, HIGH;

        public static TransactionValue byValue(BigInteger value) {
            if (value.compareTo(new BigInteger("300")) < 0) {
                return LOW;
            } else if (value.compareTo(new BigInteger("700")) > 0) {
                return HIGH;
            } else return MEDIUM;
        }
    }

    public enum TransactionTime {
        HISTORICAL, FUTURE;

        public static TransactionTime byDate(LocalDateTime date) {
            if (date.isAfter(LocalDateTime.now())) {
                return FUTURE;
            } else return HISTORICAL;
        }

    }
}
