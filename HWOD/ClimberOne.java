public class ClimberOne {
    public static void main(String args[]) {
        int[] mapArray = {0, 1, 2, 3, 2, 1, 0, 4, 3, 0};
        int[] mapArray2 = {0, 0, 0, 0, 0};
        System.out.println(peaks(mapArray));
        System.out.println(peaks(mapArray2));
    }

    public static int peaks(int[] mapArray) {
        int peakCount = 0;
        for (int i = 0; i < mapArray.length; i++) {
            if (i == 0 && mapArray[i] > mapArray[i + 1]) {
                peakCount++;
            }

            if (i == mapArray.length - 1 && mapArray[i] > mapArray[i - 1]) {
                peakCount++;
            }

            if (i > 0 && i < mapArray.length - 1 && mapArray[i] > mapArray[i - 1] && mapArray[i] > mapArray[i + 1]) {
                peakCount++;
            }
        }
        return peakCount;
    }
}
