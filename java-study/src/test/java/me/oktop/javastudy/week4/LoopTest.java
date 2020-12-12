package me.oktop.javastudy.week4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoopTest {

    @Test
    @DisplayName("반복문 - whild문 구구단 2단")
    void loop_while_test() {
        int i = 1;
        while (i < 10) {
            System.out.println(String.format("%s*%s=%s", 2, i, 2 * i));
            i++;
        }
    }

    @Test
    @DisplayName("반복문 - 1단계 for문 구구단 2단")
    void loop_for_1depth_test() {
        int num = 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            sb.append(String.format("%s*%s=%s", num, i, num * i)).append("\n");
        }
        System.out.println(sb.toString());
    }

    @Test
    @DisplayName("반복문 - 2단계 for문 구구단 2~3단")
    void loop_for_2depth_test() {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= 3; i++) {
            for (int j = 1; j <= 9; j++) {
                sb.append(String.format("%s*%s=%s", i, j, i * j)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

}
