public class MergeSort {
    public static void main(String[] args) {
        int[] numbers = {6, 5, 3, 1, 7, 2, 4};

        for(int i = 0; i < numbers.length; i++){
            System.out.print(numbers[i] + " ");
        }
        System.out.print("\n");

        mergeSort(numbers, 0, numbers.length - 1);
        for(int i = 0; i < numbers.length; i++){
            System.out.print(numbers[i] + " ");
        }
    }

    public static void mergeSort(int[] list, int left, int right) {
        if(left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(list, left, mid);
        mergeSort(list, mid + 1, right);
        merge(list, left, mid, right);
    }

    public static void merge(int[] list, int left, int mid, int right){
        int[] tmp = new int[list.length];
        int start = left;
        int rightIndex = mid + 1;//对应第24行的mid + 1
        int tmpIndex = left;
        while(left <= mid && rightIndex <= right){
            if(list[left] <= list[rightIndex]){
                tmp[tmpIndex++] = list[left++];
            }else{
                tmp[tmpIndex++] = list[rightIndex++];
            }
        }
        while(left <= mid){
            tmp[tmpIndex++] = list[left++];
        }
        while(rightIndex <= right){
            tmp[tmpIndex++] = list[rightIndex++];
        }
        while(start <= right){
            list[start] = tmp[start];
            start++;
        }
    }
}