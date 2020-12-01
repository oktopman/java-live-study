package me.oktop.javastudy.practice;

public class XOR {

    public static void main(String[] args) {
        XOR xor = new XOR();
        System.out.println(xor.solution(new int[]{1, 3, 5, 6, 1, 3, 6}));
    }

    private int solution(int[] ints) {
        int result = 0;
        for (int num : ints) {
            result ^= num;
        }
        return result;
    }
}
