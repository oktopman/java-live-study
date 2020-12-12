package me.oktop.javastudy.week4;

public class Queue {

    private int[] queue;
    private int head;
    private int tail;
    private int size;

    public Queue(int size) {
        this.queue = new int[size];
        this.head = -1;
        this.tail = 0;
        this.size = size;
    }

    public void push(int value) {

        this.queue[++head] = value;
    }

    public int pop() {
        return this.queue[this.tail++];
    }

    public boolean isFull() {
        return this.head + 1 == this.size;
    }

    public int[] getQueue() {
        return queue;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }
}
