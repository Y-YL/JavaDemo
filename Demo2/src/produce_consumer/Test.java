package produce_consumer;

import myblockQueue.IBlockQueue;
import myblockQueue.MyBlockQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Test {
    public static void main(String[] args) {
        IBlockQueue<Integer> queue = new IBlockQueue<Integer>(100);
        Producer p = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}
