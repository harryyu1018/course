package top.yujy.Stream;

import top.yujy.Stream.domain.Transaction;

import java.util.List;
import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toSet;

/**
 * 交易流操作的栗子.
 */
public class TransactionStreamDemo {

    private static List<Transaction> transactions = Transaction.mock();

    public static void main(String[] args) {
//        list2011AndSortByValue();
        listCities();
    }

    public static void list2011AndSortByValue() {
        transactions.stream()
                    .filter(t -> t.getYear() == 2011)
                    .sorted(comparing(Transaction::getValue))
                    .collect(toList())
                    .forEach(System.out::println);
    }

    public static void listCities() {
        transactions.stream()
                    .map(ts -> ts.getTrader().getCity())
                    .distinct()
                    .forEach(System.out::println);

//        transactions.stream()
//                .map(ts -> ts.getTrader().getCity())
//                .collect(toSet())
//                .forEach(System.out::println);
    }
}
