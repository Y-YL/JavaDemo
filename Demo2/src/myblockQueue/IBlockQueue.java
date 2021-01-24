package myblockQueue;

/**
 *  使用数组模拟一个简单的阻塞队列
 * @param <E>
 */
public class IBlockQueue<E> {

    //当前长度
    private int count;
    // 头节点位置
    private int head;
    // 尾节点位置
    private int tail;

    // 存储数组
    private Object[] arr;

    public  IBlockQueue(int size){
        head = 0;
        tail = 0;
        arr = new Object[size];
    }

    // 添加元素
    public  synchronized void put(E data){
        while (count>=arr.length){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        arr[tail] = data;
        count++;
        this.tail = (tail+1)%arr.length;
        notifyAll();
    }

    // 取出元素
    public synchronized E take(){
        while (count<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        E data = (E) arr[head];
        this.head = (head+1)%arr.length;
        count--;
        notifyAll();
        return data;
    }

    public synchronized int size(){
        return arr.length;
    }

}
