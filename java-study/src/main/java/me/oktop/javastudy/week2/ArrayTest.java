package me.oktop.javastudy.week2;

import java.util.HashMap;
import java.util.Map;

public class ArrayTest {

    public static void main(String[] args) {
        int[] array = new int[3];
        array[0] = 10;
        array[1] = 20;
        array[2] = 30;

        System.out.println("1차원 배열");
        for (int i : array) {
            System.out.println(i);
        }

        int[][] array2 = new int[2][3];
        array2[0][0] = 10;
        array2[0][1] = 20;
        array2[0][2] = 30;
        array2[1][0] = 40;
        array2[1][1] = 50;
        array2[1][2] = 60;

        System.out.println("2차원 배열");
        for (int[] arr : array2) {
            for (int i : arr) {
                System.out.println(i);
            }
        }
        Map<String, Object> map = new HashMap<>();
    }
}
