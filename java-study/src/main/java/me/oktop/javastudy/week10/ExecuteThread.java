package me.oktop.javastudy.week10;

public class ExecuteThread {

    public int num = 100;

    public static void main(String[] args) {
        ExecuteThread executeThread = new ExecuteThread();
        MyThread myThread = new MyThread();
        Runnable runnable = new MyThread2();
        Thread myThread2 = new Thread(runnable);
        myThread.start();
        myThread2.start();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
