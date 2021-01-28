package produce_consumer;

import myblockQueue.IBlockQueue;
import myblockQueue.MyBlockQueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    private final IBlockQueue<Integer> queue;


    public Consumer(IBlockQueue<Integer> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                consume(queue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void consume(Integer n){
        System.out.println("Thread-"+Thread.currentThread().getId()+" consume:"+n);
    }
}
