import java.util.*;

public class ContinuousNumberSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();

        System.out.println(target + "=" + target);

        List<String> expressions = new ArrayList<>();

        // 对于输入数据量 < 10000的情况，可以考虑使用暴力破解法
        for (int i = 1; i < target; i++) {
            int sum = 0;

            StringBuilder sb = new StringBuilder();
            for (int j = i; sum < target; j++) {
                sum += j;
                sb.append(j).append("+");

                if (sum == target) {
                    expressions.add(target + "=" + sb.substring(0, sb.length() - 1));
                    break;
                }
            }
        }

        expressions.sort(Comparator.comparingInt(String::length));

        for (String expression : expressions) {
            System.out.println(expression);
        }

        System.out.println("Result:" + (expressions.size() + 1));
    }
}
