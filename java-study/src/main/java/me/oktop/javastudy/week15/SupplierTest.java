package me.oktop.javastudy.week15;

import java.util.function.Supplier;

public class SupplierTest {


    public static void main(String[] args) {
        int a = 1;
        print(() -> {
            return "hi" + a;
        });
        print(() -> {
            return "hello" + a;
        });
    }


    public static void print(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }
}
