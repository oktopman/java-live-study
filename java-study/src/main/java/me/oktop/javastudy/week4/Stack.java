package me.oktop.javastudy.week4;

public class Stack {
    private int [] stack = new int[20];
    private int current;
    private int next;

    public void push(int data) {
        stack[next] = data;
        current = next;
        next++;
    }

    public int pop() {
        int num = stack[current];
        stack[current] = 0;
        return num;
    }

    public int[] getStack() {
        return stack;
    }

    public int getNext() {
        return next;
    }

    public int getCurrent() {
        return current;
    }
}
