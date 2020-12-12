package me.oktop.javastudy.week4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SwitchCaseTest {

    @Test
    @DisplayName("switch 문으로 구구단 구현")
    void print_multiplication_tables_test() {
        int num = 5;
        String s = null;
        switch (num) {
            case 2 :
                s = generate_multiplication_tables(num);
                break;
            case 3 :
                s = generate_multiplication_tables(num);
                break;
            case 4 :
                s = generate_multiplication_tables(num);
                break;
            case 5 :
                s = generate_multiplication_tables(num);
                break;
            case 6 :
                s = generate_multiplication_tables(num);
                break;
            case 7 :
                s = generate_multiplication_tables(num);
                break;
            case 8 :
                s = generate_multiplication_tables(num);
                break;
            case 9 :
                s = generate_multiplication_tables(num);
                break;
        }
        System.out.println(s);
    }

    String generate_multiplication_tables(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            sb.append(String.format("%s*%s=%s", num, i, num * i)).append("\n");
        }
        return sb.toString();
    }
}
