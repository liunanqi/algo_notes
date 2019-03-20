import java.util.concurrent.*;

public class MyBlockingQueue {
    //无需自己加锁，直接使用封装好的共享资源，底层自动同步
    private static ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(2);

    public static void  main(String[] args){
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
                    //buffer.put()内部实现使用了notFull信号量
                    buffer.put(i++);
                    Thread.sleep((int) (Math.random() * 10000));
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
                    //buffer.take()内部实现使用了notEmpty信号量
                    System.out.println("\t\t\tConsumer reads " + buffer.take());
                    Thread.sleep((int) (Math.random() * 10000));
                }
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}