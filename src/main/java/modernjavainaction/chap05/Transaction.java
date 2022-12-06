package modernjavainaction.chap05;

import java.util.*;
import java.util.stream.Collectors;

public class Transaction {

  private Trader trader;
  private int year;
  private int value;

  public Transaction(Trader trader, int year, int value) {
    this.trader = trader;
    this.year = year;
    this.value = value;
  }

  public Trader getTrader() {
    return trader;
  }

  public int getYear() {
    return year;
  }

  public int getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    int hash = 17;
    hash = hash * 31 + (trader == null ? 0 : trader.hashCode());
    hash = hash * 31 + year;
    hash = hash * 31 + value;
    return hash;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Transaction)) {
      return false;
    }
    Transaction o = (Transaction) other;
    boolean eq = Objects.equals(trader,  o.getTrader());
    eq = eq && year == o.getYear();
    eq = eq && value == o.getValue();
    return eq;
  }

  @SuppressWarnings("boxing")
  @Override
  public String toString() {
    return String.format("{%s, year: %d, value: %d}", trader, year, value);
  }

  public static void main(String[] args) {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");


    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300)
            , new Transaction(raoul, 2012, 1000)
            , new Transaction(raoul, 2011, 400)
            , new Transaction(mario, 2012, 710)
            , new Transaction(mario, 2012, 700)
            , new Transaction(alan, 2012, 950)
    );

    List<Transaction> q1 = transactions.stream()
            .filter(v -> v.getYear() == 2011)
            .sorted(Comparator.comparing(Transaction::getValue))
            .collect(Collectors.toList());

    System.out.println("q1");
    q1.forEach(System.out::println);

    List<String> q2 = transactions.stream()
            .map(Transaction::getTrader)
            .map(Trader::getCity)
            .distinct()
            .collect(Collectors.toList());

    System.out.println("q2");
    System.out.println(q2);

    List<Trader> q3 = transactions.stream()
            .map(Transaction::getTrader)
            .filter(v -> "Cambridge".equals(v.getCity()))
            .distinct()
            .sorted(Comparator.comparing(Trader::getName))
            .collect(Collectors.toList());

    System.out.println("q3");
    System.out.println(q3);


    String q4 = transactions.stream()
            .map(Transaction::getTrader)
            .map(Trader::getName)
            .distinct()
            .sorted()
            .collect(Collectors.joining());

    System.out.println("q4");
    System.out.println(q4);

    boolean hasTraderInMilano = transactions.stream()
            .anyMatch(v -> "Milan".equals(v.getTrader().getCity()));


    System.out.println("q5");
    System.out.println("Milan에 거래자가 있는가? " + hasTraderInMilano);


    List<Integer> q6 = transactions.stream()
            .filter(v -> "Cambridge".equals(v.getTrader().getCity()))
            .map(Transaction::getValue)
            .collect(Collectors.toList());
    System.out.println("q6");
    System.out.println(q6);


    Optional<Integer> q7 = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::max);

    System.out.println("q7");
    System.out.println(q7.get());

    Optional<Transaction> q8 = transactions.stream()
            //.map(Transaction::getValue)
            //.reduce(Integer::min);
            .min(Comparator.comparing(Transaction::getValue));

    System.out.println("q8");
    System.out.println(q8.get().getValue());
  }
}
