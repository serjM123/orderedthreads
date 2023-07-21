package org.example;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class App {
    static AtomicInteger counter = new AtomicInteger(0);
    static List<CustomThread> threads;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threads = List.of(
                new CustomThread("Thirst"),
                new CustomThread("Second"),
                new CustomThread("Third"),
                new CustomThread("Forth"));
        threads.forEach(threadPool::submit);
        threads.get(0).start();
        sleep(1000);
        threadPool.shutdownNow();
    }

    static class CustomThread implements Runnable {
        private final String name;
        private final SynchronousQueue<Boolean> queue = new SynchronousQueue<>();

        public CustomThread(final String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    queue.take();
                    sleep(50);
                    System.out.println(name);
                    next();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        private void next() throws InterruptedException {
            int m = counter.incrementAndGet() % threads.size();
            threads.get(m).start();
        }

        public void start() throws InterruptedException {
            queue.put(true);
        }
    }
}