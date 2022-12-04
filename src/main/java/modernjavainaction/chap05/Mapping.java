package modernjavainaction.chap05;

import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap04.Dish.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import modernjavainaction.chap04.Dish;

public class Mapping{

  public static void main(String... args) {
    // map
    List<String> dishNames = menu.stream()
        .map(Dish::getName)
        .collect(toList());
    System.out.println(dishNames);

    // map
    List<String> words = Arrays.asList("Hello", "World");
    List<Integer> wordLengths = words.stream()
        .map(String::length)
        .collect(toList());
    System.out.println(wordLengths);

    // flatMap
    words.stream()
        .flatMap((String line) -> Arrays.stream(line.split("")))
        .distinct()
        .forEach(System.out::println);

    // flatMap
    List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
    List<Integer> numbers2 = Arrays.asList(6,7,8);
    List<int[]> pairs = numbers1.stream()
        .flatMap((Integer i) -> numbers2.stream()
            .map((Integer j) -> new int[]{i, j})
        )
        .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
        .collect(toList());
    pairs.forEach(pair -> System.out.printf("(%d, %d)\n", pair[0], pair[1]));

    //QUIZ 5-2-1
    //숫자 리스트가 주어졌을 때 각 숫자의 제곱근으로 이루어진 리스트를 반환

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

    List<Integer> result = numbers.stream()
            .map(number -> Math.pow(number, 2))
            .map(Double::intValue)
            .collect(Collectors.toList());
    System.out.println(result);

    //QUIZ 5-2-2
    //두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환
    List<Integer> Q5_numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> Q5_numbers2 = Arrays.asList(3, 4);

    List<int[]> result2 = Q5_numbers1.stream()
            .flatMap(a -> Q5_numbers2.stream().map(b -> new int[]{a, b}))
            .distinct()
            .collect(Collectors.toList());

    result2.forEach(v -> System.out.printf("(%d, %d)", v[0],v[1]));
    System.out.println();
    //QUIZ 5-2-3
    //5-2-2에서 숫자의 합이 3으로 나누어 떨어지는 리스트

    List<int[]> result3 = Q5_numbers1.stream()
            .flatMap(a -> Q5_numbers2.stream()
                    .filter(b -> (a+b)%3 == 0)
                    .map(b -> new int[]{a, b}))
            .distinct()
            .collect(Collectors.toList());

    result3.forEach(v -> System.out.printf("(%d, %d)", v[0],v[1]));


  }
}
