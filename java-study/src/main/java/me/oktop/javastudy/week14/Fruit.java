package me.oktop.javastudy.week14;

public class Fruit<T> {

    private T id;
    private String color;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", color='" + color + '\'' +
                '}';
    }
}
