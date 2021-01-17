package me.oktop.javastudy.week9;

public class CustomExceptionExample {

    public static void main(String[] args) {
        if (true) {
            throw new CustomException("error");
        }
    }
}
