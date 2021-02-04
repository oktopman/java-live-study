package me.oktop.javastudy.week12;

public class Users {

    @UserAnnotation(number = 10)
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
