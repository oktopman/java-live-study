package me.oktop.javastudy.week14;

import java.util.Arrays;
import java.util.List;

public class WildCardTest {

    public static void print(List<? super Integer> list) {
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        List<Number> numbers = Arrays.asList(1, 2, 3, 4);
        List<String> strings = Arrays.asList("a", "b", "c", "d");

        print(integers);
        print(numbers);
//        print(strings);
    }
}
