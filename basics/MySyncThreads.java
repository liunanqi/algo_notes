import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

public class MySyncThreads {
    private static Account account = new Account();
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());

        executor.shutdown();

        System.out.println("The balance is:\n" + account.getBalance());
    }

    public static class DepositTask implements Runnable{
        public void run(){
            try{
                while(true){
                    account.deposit((int) (Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static class WithdrawTask implements Runnable{
        public void run(){
            while(true){
                account.withdraw((int)(Math.random() * 10) + 1);
            }
        }
    }

    private static class Account{
        private static Lock lock = new ReentrantLock();
        private static Condition newDeposit = lock.newCondition();
        private int balance = 0;

        public int getBalance(){
            return balance;
        }

        public void withdraw(int amount){
            //必须首先拥有锁才能调用await，signalAll方法
            lock.lock();
            try{
                while(balance < amount){
                    System.out.println("\t\t\twait for a deposit");
                    newDeposit.await();
                }
                balance -= amount;
                System.out.println("\t\t\twithdraw " + amount + "\t\t" + getBalance());
            }catch(InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void deposit(int amount){
            lock.lock();
            try{
                balance += amount;
                System.out.println("Deposit " + amount + "\t\t\t" + getBalance());
                newDeposit.signalAll();

            }
            finally {
                lock.unlock();
            }
        }
    }
}