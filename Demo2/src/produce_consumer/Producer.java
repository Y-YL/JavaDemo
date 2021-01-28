package produce_consumer;

import myblockQueue.IBlockQueue;
import myblockQueue.MyBlockQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

// 生产者
public class Producer implements Runnable{

   private final IBlockQueue<Integer> queue;


    public Producer(IBlockQueue<Integer> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        try {
            while (true){

                Thread.sleep(100);
                queue.put(produce());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int produce(){
        Random random = new Random();
        int n = random.nextInt(100000);
        System.out.println("Thread-"+Thread.currentThread().getId()+" produce:"+n);
        return n;
    }

}
