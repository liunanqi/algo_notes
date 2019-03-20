import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutors {
    public static void main(String[] args){
        //使用了LinkedBlockingQueue, ThreadFactory
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new PrintChar('a', 30));
        executor.execute(new PrintChar('b', 30));
        executor.execute(new PrintChar('c', 30));
        executor.shutdown();
    }
}