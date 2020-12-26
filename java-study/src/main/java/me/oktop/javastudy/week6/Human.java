package me.oktop.javastudy.week6;

import java.time.LocalDate;

public class Human extends Base {
    public String name;
    public int age;

    public Human() {
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void plusAge() {
        this.age++;
    }

    public void introduceMySelf() {
        System.out.println(String.format("제이름은 %s 이고 나이는 %s 입니다.", this.name, this.age));
    }

    public final void finalMethod() {
        System.out.println("final mehotd 입니다.");
    }

    @Override
    LocalDate getCreatedDate() {
        return LocalDate.now();
    }
}
