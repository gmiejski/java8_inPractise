package java8.model.transaction;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Transaction {

    private final BigInteger value;
    private final LocalDateTime date;
    private final Traders traders;

    public Transaction(BigInteger value, LocalDateTime date, Traders traders) {
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
}
