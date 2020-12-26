package me.oktop.javastudy.week6;

import me.oktop.javastudy.week4.ListNode;

public class Development extends Human {

    public void printAndPlusAge() {
        System.out.println(String.format("현재나이는 %s 입니다.", getAge()));
        System.out.println("1년후.......");
        super.plusAge();
        System.out.println(String.format("현재나이는 %s 입니다.", getAge()));
    }

    @Override
    public void introduceMySelf() {
        System.out.println(String.format("제 이름은 %s 입니다.", this.name));
    }


}
