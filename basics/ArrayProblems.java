import java.util.HashMap;
import java.util.Map;

public class ArrayProblems {
    public int majorityElement(int[] nums) {
        //奇技淫巧
        //Arrays.sort(nums);
        //return nums[nums.length/2];
        int n = nums.length / 2;
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], 1);
            }else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        for(Map.Entry entry : map.entrySet()){
            if((int)entry.getValue() > n)
                result = (int) entry.getKey();
        }
        return result;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) return false;
        int col = matrix[0].length - 1;
        int row = 0;
        while (col >= 0 && row <= matrix.length - 1){
            if(target == matrix[row][col]){
                return true;
            }else if(target < matrix[row][col]){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }
}