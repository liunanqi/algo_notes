import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class HotPages {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.nextLine());
        String[] accesses = scanner.nextLine().split(" ");
        int threshold = Integer.parseInt(scanner.nextLine());
        scanner.close();

        Map<Integer, Integer> freMap = new TreeMap<>();
        for (String access : accesses) {
            int pageFrame = Integer.parseInt(access);
            freMap.put(pageFrame, freMap.getOrDefault(pageFrame, 0) + 1);
        }

        PriorityQueue<Integer> hotPages = new PriorityQueue<>((a, b) -> {
            int freComp = freMap.get(b).compareTo(freMap.get(a));
            if (freComp == 0) {
                return a.compareTo(b);
            }
            return freComp;
        });

        for (Map.Entry<Integer, Integer> entry : freMap.entrySet()) {
            if (entry.getValue() >= threshold) {
                hotPages.offer(entry.getKey());
            }
        }

        int hotCount = hotPages.size();
        System.out.println(hotCount);
        while(!hotPages.isEmpty()){
            System.out.println(hotPages.poll());
        }
    }
}
