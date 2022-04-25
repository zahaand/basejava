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

        MyThreadThread thread4 = new MyThreadThread();
        thread4.start();
    }

    // deadLock #1
    static Thread thread1 = new Thread(() -> {
        System.out.println("thread1 run");
        synchronized (LOCK_1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK_2) {
                System.out.println("thread1 done");
            }
        }
    });

    static Thread thread2 = new Thread(() -> {
        System.out.println("thread2 run");
        synchronized (LOCK_2) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK_1) {
                System.out.println("thread2 done");
            }
        }
    });

    // deadLock #2
    static class MyThreadRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyThreadRunnable run");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK_2) {
                System.out.println("thread1 done");
            }
        }
    }

    static class MyThreadThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThreadThread run");
            synchronized (LOCK_2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_1) {
                    System.out.println("MyThread2 done");
                }
            }
        }
    }
}

