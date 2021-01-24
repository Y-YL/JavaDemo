package myblockQueue;

import java.util.Objects;

public class MyBlockQueue<E> {

    // 当前数量
    private int count;
    // 头节点
    private int head;
    // 尾节点
    private int tail;

    private Object[] arr;

    public MyBlockQueue(int arrLen) {
        this.count = 0;
        this.head = 0;
        this.tail = 0;
        arr = new Object[arrLen];
    }

    public synchronized void put(E data) {
        while (count >= arr.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        arr[tail] = data;
        this.tail = (tail + 1) % arr.length;
        count++;
        notifyAll();
    }

    public synchronized E take() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        E str = (E)arr[head];
        this.head = (head + 1) % arr.length;
        count--;
        notifyAll();
        return str;
    }

    public synchronized int size() {
        return arr.length;
    }

}
