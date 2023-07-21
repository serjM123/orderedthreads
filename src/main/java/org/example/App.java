package org.example;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static java.lang.Thread.sleep;

public class App {
    static AtomicInteger counter = new AtomicInteger(0);
    static List<CustomThread> threads;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Function<Integer, Integer> task = (t) -> t;
        threads = List.of(
                new CustomThread("First", task),
                new CustomThread("Second", task),
                new CustomThread("Third", task),
                new CustomThread("Forth", task));
        threads.forEach(threadPool::submit);
        threads.get(0).start(1);
        sleep(1000);
        threadPool.shutdownNow();
    }

    static class CustomThread<T, R> implements Runnable {
        private final String name;
        private final Function<T, R> task;
        private final SynchronousQueue<R> queue = new SynchronousQueue<R>();

        public CustomThread(final String name, final Function<T, R> task) {
            this.name = name;
            this.task = task;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    T t = (T) queue.take();
                    R r = task.apply(t);
                    sleep(50);
                    System.out.println(name);
                    next(r);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        private void next(final R r) throws InterruptedException {
            int m = counter.incrementAndGet() % threads.size();
            threads.get(m).start(r);
        }

        public void start(final R r) throws InterruptedException {
            queue.put(r);
        }
    }
}