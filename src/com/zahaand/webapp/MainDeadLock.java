package com.zahaand.webapp;

public class MainDeadLock {
    static final Object LOCK_1 = new Object();
    static final Object LOCK_2 = new Object();
    static boolean isLocked = false;

    public static void main(String[] args) {
        thread1.start();
        thread2.start();

        MyThreadRunnable runnable = new MyThreadRunnable();
        Thread thread3 = new Thread(runnable);
        thread3.start();

        MyThread thread4 = new MyThread();
        thread4.start();
    }

    // deadLock #1
    static Thread thread1 = new Thread(() -> {
        System.out.println("thread1 run");
        doLock(LOCK_1, LOCK_2);
    });

    static Thread thread2 = new Thread(() -> {
        System.out.println("thread2 run");
        doLock(LOCK_2, LOCK_1);
    });

    // deadLock #2
    static class MyThreadRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyThreadRunnable run");
            doLock(LOCK_1, LOCK_2);
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread run");
            doLock(LOCK_2, LOCK_1);
        }
    }

    static private void doLock(Object o1, Object o2) {
        synchronized (o1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o2) {
                System.out.println("doLock done");
            }
        }
    }
}

