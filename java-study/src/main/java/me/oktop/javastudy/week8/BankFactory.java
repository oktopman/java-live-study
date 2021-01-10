package me.oktop.javastudy.week8;

public class BankFactory {

    public static Bank getBank(String type) {
        if (type.equals("kb")) {
            return new KBBank();
        } else if (type.equals("woori")) {
            return new WooriBank();
        }
        return null;
    }
}
