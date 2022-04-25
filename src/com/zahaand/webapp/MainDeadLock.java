package com.zahaand.webapp;

public class MainDeadLock {
    static final Object LOCK_1 = new Object();
    static final Object LOCK_2 = new Object();

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
        doLock1();
    });

    static Thread thread2 = new Thread(() -> {
        System.out.println("thread2 run");
        doLock2();
    });

    // deadLock #2
    static class MyThreadRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyThreadRunnable run");
            doLock1();
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread run");
            doLock2();
        }
    }

    static private void doLock1() {
        synchronized (LOCK_1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK_2) {
                System.out.println("doLock1 done");
            }
        }
    }

    private static void doLock2() {
        synchronized (LOCK_2) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK_1) {
                System.out.println("doLock2 done");
            }
        }
    }
}

