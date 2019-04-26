import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> inLoop = new HashSet<Integer>();
        int squareSum,remain;
        while (inLoop.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n%10;
                squareSum += remain*remain;
                n /= 10;
            }
            if (squareSum == 1)
                return true;
            else
                n = squareSum;

        }
        return false;
    }
	
//    public boolean isHappy(int n) {
//        if(n == 1 || n == 7) return true;
//        else if(n < 10) return false;
//        int m = 0;
//        while(n != 0){
//            int tail = n % 10;
//            m += tail * tail;
//            n = n/10;
//        }
//        return isHappy(m);
//    }
}