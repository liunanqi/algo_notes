public class MathUtils {
    public static int[] generateIntArray(int length, int range){
        int[] result = new int[length];
        for(int i = 0; i < result.length; i++){
            result[i] = (int) Math.floor(Math.random() * range + 1);
        }
        return result;
    }

}