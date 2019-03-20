import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

public class ConsumerAndProducer {
    private static Buffer buff = new Buffer();

    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new ProducerTask());
        executor.execute(new ConsumerTask());
        executor.shutdown();
    }

    private static class ProducerTask implements Runnable{
        @Override
        public void run() {
            try{
                int i = 1;
                while(true){
                    System.out.println("Producer writes " + i);
                    buff.write(i++);
                    Thread.sleep((int)(Math.random() * 10000));
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static class ConsumerTask implements Runnable{
        @Override
        public void run() {
            try{
                while(true){
                    System.out.println("\t\t\tConsumer reads " + buff.read());
                    Thread.sleep((int)(Math.random() * 10000));
                }
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //定义缓冲区（共享资源）,对共享资源加锁
    private static class Buffer{
        //缓冲区容量
        private static final int CAP = 1;
        private LinkedList<Integer> queue = new LinkedList<>();

        private static Lock lock = new ReentrantLock();

        private static Condition notEmpty = lock.newCondition();
        private static Condition notFull = lock.newCondition();

        public void write(int value){
            lock.lock();
            try{
                while(queue.size() == CAP){
                    System.out.println("Waiting for notFull condition...");
                    notFull.await();
                }

                queue.offer(value);
                notEmpty.signal();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            finally{
                lock.unlock();
            }
        }

        public int read(){
            int value = 0;
            lock.lock();
            try{
                while(queue.isEmpty()){
                    System.out.println("Waiting for notEmpty condition...");
                    notEmpty.await();
                }
                value = queue.remove();
                notFull.signal();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            finally {
                lock.unlock();
                return value;
            }
        }
    }
}