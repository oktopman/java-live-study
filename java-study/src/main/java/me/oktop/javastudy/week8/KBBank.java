package me.oktop.javastudy.week8;

public class KBBank implements Bank {

    @Override
    public void deposit(int price) {
        System.out.println("kbbank deposit : " + price);
    }

    @Override
    public void withDraw(int price) {
        System.out.println("kbbank withDraw : " + price);
    }
}
