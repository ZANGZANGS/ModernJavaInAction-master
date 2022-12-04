package modernjavainaction.chap04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Dish {

  private final String name;
  private final boolean vegetarian;
  private final int calories;
  private final Type type;

  public Dish(String name, boolean vegetarian, int calories, Type type) {
    this.name = name;
    this.vegetarian = vegetarian;
    this.calories = calories;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public boolean isVegetarian() {
    return vegetarian;
  }

  public int getCalories() {
    return calories;
  }

  public Type getType() {
    return type;
  }

  public enum Type {
    MEAT,
    FISH,
    OTHER
  }

  @Override
  public String toString() {
    return name;
  }

  public static final List<Dish> menu = Arrays.asList(
      new Dish("pork", false, 800, Dish.Type.MEAT),
      new Dish("beef", false, 700, Dish.Type.MEAT),
      new Dish("chicken", false, 400, Dish.Type.MEAT),
      new Dish("french fries", true, 530, Dish.Type.OTHER),
      new Dish("rice", true, 350, Dish.Type.OTHER),
      new Dish("season fruit", true, 120, Dish.Type.OTHER),
      new Dish("pizza", true, 550, Dish.Type.OTHER),
      new Dish("prawns", false, 400, Dish.Type.FISH),
      new Dish("salmon", false, 450, Dish.Type.FISH)
  );

  public static void main(String[] args) {
    /**
     * QUIZ 4-1 외부 반복을 참고해서 스트림 동작을 사용하여 리팩토링
     */
    List<String> highCaloricDishes = new ArrayList<>();
    Iterator<Dish> iterator = menu.iterator();

    while (iterator.hasNext()) {
      Dish dish = iterator.next();
      if (dish.getCalories() > 300) {
        highCaloricDishes.add(dish.getName());
      }
    }
    System.out.println("iterator >>>\n" + highCaloricDishes);

    /**
     * QUIZ 4-1 정답
     */
    highCaloricDishes =menu.stream()
            .filter(dish -> dish.getCalories() > 300)
            .map(Dish::getName)
            .collect(Collectors.toList());

    System.out.println("stream >>>\n" + highCaloricDishes);
  }
}
