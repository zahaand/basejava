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
        doLock();
    });

    static Thread thread2 = new Thread(() -> {
        System.out.println("thread2 run");
        doLock();
    });

    // deadLock #2
    static class MyThreadRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyThreadRunnable run");
            doLock();
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread run");
            doLock();
        }
    }

    static private void doLock() {
        if (!isLocked) {
            synchronized (LOCK_1) {
                isLocked = true;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_2) {
                    System.out.println("LOCK_1 done");
                }
            }
        } else {
            synchronized (LOCK_2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_1) {
                    System.out.println("LOCK_2 done");
                }
            }
        }
    }
}

