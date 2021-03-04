package me.oktop.javastudy.week15;

import java.time.LocalDate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class FeeCalculatorTest {

    public static void main(String[] args) {

        LocalDate oneDayAfter = LocalDate.of(2021, 3, 2);
        LocalDate twoDayAfter = LocalDate.of(2021, 3, 3);
        LocalDate today = LocalDate.of(2021, 3, 2);
        FeeCalculator feeCalculator = null;
        if (today.isEqual(oneDayAfter)) {
            // 이자를 100원 추가
            feeCalculator = (money) -> money + 100;
        } else if (today.isEqual(twoDayAfter)) {
            // 이자를 200원 추가
            feeCalculator = (money) -> money + 200;
        }
        int fee = feeCalculator.getFee(300);
        System.out.println(fee);

        IntPredicate intPredicate = i -> i == 0; // 오토 언박싱 X
        Predicate<Integer> predicate = i2 -> i2 == 0; // 오토 언박싱

    }
}
