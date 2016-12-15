package top.yujy.Stream;

import top.yujy.Stream.domain.Dish;

import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;

/**
 * 对Dish对象的Stream操作栗子。
 */
public class DishStreamDemo {

    public static void main(String[] args) {
        getThreeHighCaloricDishName();
    }

    public static void getThreeHighCaloricDishName() {

        List<Dish> menu = Dish.mock();
        List<String> threeHighCaloricDishName =
                menu.stream()
                    .filter(d -> d.getCalories() > 300)
                    .sorted(comparing(Dish::getCalories))
                    .map(Dish::getName)
                    .limit(3)
                    .collect(toList());

        System.out.println(threeHighCaloricDishName);
    }
}
