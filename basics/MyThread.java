public class MyThread{
    public static void main(String[] args){

        Runnable printA = new PrintChar('a', 30);
        Runnable printB = new PrintChar('b', 30);
        Runnable print100 = new PrintNum(30);

        Thread threadA = new Thread(printA);
        Thread threadB = new Thread(printB);
        Thread thread100 = new Thread(print100);

        //不能用run() 替代 start(),否则线程会依次执行完

        thread100.start();
        threadA.start();
        threadB.start();

    }
}

class PrintNum implements Runnable{
    private int num;

    public PrintNum(int n){
        num = n;
    }

    public void run(){
        for(int i = 1; i <= num; i++){
            System.out.print(i);
        }

    }
}

class PrintChar implements Runnable{
    private char charToPrint;
    private int times;

    public PrintChar(char c, int t){
        charToPrint = c;
        times = t;
    }

    public void run(){
        for(int i = 0; i < times; i++){
            System.out.print(charToPrint);
        }
    }
}