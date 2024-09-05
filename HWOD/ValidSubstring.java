import java.util.Scanner;

public class ValidSubstring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String stringS = scanner.nextLine();
        String stringL = scanner.nextLine();

        int indexS = 0;
        int indexL = 0;

        while (indexS < stringS.length() && indexL < stringL.length()) {
            if (stringS.charAt(indexS) == stringL.charAt(indexL)) {
                indexS++;
            }
            indexL++;
        }

        if (indexS == stringS.length()) {
            System.out.println(indexL - 1);
        } else {
            System.out.println(-1);
        }
    }
}
