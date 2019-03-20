public class QuickSort {
    public static void main(String[] args){
        int[] eg = {3, 5, 1, 4, 6, 7, 2, 4, 3};
        quickSort(eg);
        for(int i = 0; i < eg.length; i++){
            System.out.println(eg[i]);
        }
    }

    public static void quickSort(int[] list){
        quickSort(list, 0, list.length - 1);
    }

    public static void quickSort(int[] list, int first, int last) {
        if(last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    public static int partition(int[] list, int first, int last) {
        int pivot = list[first];
        int low = first + 1;
        int high = last;
        while(high > low){
            while(low <= high && list[low] < pivot){
                low++;
            }
            while(low <= high && list[high] >= pivot){
                high--;
            }

            if(low < high){
                exch(list, low, high);
            }
        }

//        while(high > first && list[high] >= pivot){
//            high--;
//        }

        if(pivot > list[high]){
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }else {
            return first;
        }
    }

    public static void exch(int[] list, int i, int j){
        int tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }
}