package top.yujy.Stream.domain;

import java.util.Arrays;
import java.util.List;

public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final TYPE type;

    public Dish(String name, boolean vegetarian, int calories, TYPE type) {

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

    public TYPE getType() {
        return type;
    }

    public static List<Dish> mock() {
        return Arrays.asList(
                new Dish("pork", false, 800, TYPE.MEAT),
                new Dish("beef", false, 700, TYPE.MEAT),
                new Dish("chicken", false, 400, TYPE.MEAT),
                new Dish("french fries", true, 530, TYPE.OTHER),
                new Dish("rice", true, 350, TYPE.OTHER),
                new Dish("season fruit", true, 120, TYPE.OTHER),
                new Dish("pizza", true, 550, TYPE.OTHER),
                new Dish("prawns", false, 300, TYPE.FISH),
                new Dish("salmon", false, 450, TYPE.FISH)

        );
    }

    public enum TYPE { MEAT, FISH, OTHER };
}
