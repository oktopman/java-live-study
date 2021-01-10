package me.oktop.javastudy.week8;

public class WooriBank implements Bank {

    @Override
    public void deposit(int price) {
        System.out.println("WooriBank deposit : " + price);
    }

    @Override
    public void withDraw(int price) {
        System.out.println("WooriBank withDraw : " + price);
    }

    public static void main(String[] args) {
        Bank bank = BankFactory.getBank("kb");
        bank.deposit(10);
        bank = BankFactory.getBank("woori");
        bank.deposit(11);
    }
}
