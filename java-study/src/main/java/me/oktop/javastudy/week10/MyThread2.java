package me.oktop.javastudy.week10;

public class MyThread2 implements Runnable {
    ExecuteThread executeThread = new ExecuteThread();
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(10);
                executeThread.num++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(executeThread.num);
    }
}
