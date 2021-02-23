package me.oktop.javastudy.week13;

import java.math.BigDecimal;

public class Vo {

    private String name;
    private BigDecimal amount = BigDecimal.ZERO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Vo{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
