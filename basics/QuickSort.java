public class QuickSort {
    public static void main(String[] args){
        int[] eg = {3, 5, 1, 4, 6, 7, 2};
        quickSort(eg);
        for(int i = 0; i < eg.length; i++){
            System.out.println(eg[i]);
        }
    }

    public static void quickSort(int[] list){
        quickSort(list, 0, list.length - 1);
    }

    public static void quickSort(int[] list, int low, int high) {
        if(high <= low) return;
        int pivotIndex = partition(list, low, high);
        quickSort(list, low, pivotIndex - 1);
        quickSort(list, pivotIndex + 1, high);
    }

    public static int partition(int[] list, int low, int high) {
        int i = low, j = high + 1;
        int pivot = list[low];
        while(true){
            while(list[++i] < pivot){
                if(i == high)
                    break;
            }

            while(list[--j] > pivot){
                if(j == low)
                    break;
            }
            if(i >= j)
                break;
            exch(list, i, j);
        }

        exch(list, low, j);
        return j;
    }

    public static void exch(int[] list, int i, int j){
        int tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }
}