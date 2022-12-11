package modernjavainaction.chap05;

import static modernjavainaction.chap04.Dish.menu;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import modernjavainaction.chap04.Dish;

public class NumericStreams {

  public static void main(String... args) {
    List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);

    Arrays.stream(numbers.toArray())
        .forEach(System.out::println);
    int calories = menu.stream()
        .mapToInt(Dish::getCalories)
        .sum();
    System.out.println("Number of calories:" + calories);

    // max와 OptionalInt
    OptionalInt maxCalories = menu.stream()
        .mapToInt(Dish::getCalories)
        .max();

    int max;
    if (maxCalories.isPresent()) {
      max = maxCalories.getAsInt();
    }
    else {
      // 기본값을 선택할 수 있음
      max = 1;
    }
    System.out.println(max);

    // 숫자 범위
    IntStream evenNumbers = IntStream.rangeClosed(1, 100)
        .filter(n -> n % 2 == 0);
    System.out.println("USE rangeClosed: evenNumbers.count() -> " + evenNumbers.count());//50

    IntStream evenNumbers2 = IntStream.range(1, 100) // 1과 100을 포함하지 않는다.
            .filter(n -> n % 2 == 0);
    System.out.println("USE range: evenNumbers2.count() -> " + evenNumbers2.count());//49

    Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
        .flatMap(a -> IntStream.rangeClosed(a, 100)
            .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
            .map(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }));
    System.out.println("pythagoreanTriples >>>");
    pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    Stream<int[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
        .flatMap(a -> IntStream.rangeClosed(a, 100)
            .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
            .filter(t -> t[2] % 1 == 0))
        .map(array -> Arrays.stream(array).mapToInt(a -> (int) a).toArray());
    System.out.println("pythagoreanTriples2 >>>");
    pythagoreanTriples2.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    //QUIZ 5-4
    System.out.println("QUIZ 5-4 >>>");
    Stream.iterate(new int[]{0, 1},
                    v -> new int[]{v[1], v[0] + v[1]})
            .limit(20)
            .forEach(f -> System.out.println("(" + f[0] + ", " + f[1] + ")"));
  }

  public static boolean isPerfectSquare(int n) {
    return Math.sqrt(n) % 1 == 0;
  }

}
