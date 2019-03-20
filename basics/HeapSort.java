public class HeapSort {
    public static <E extends Comparable<E>> void heapSort(E[] list){
        Heap<E> heap = new Heap<>();
        for(int i = 0; i < list.length; i++){
            heap.add(list[i]);
        }

        for(int j = list.length - 1; j >=0; j--){
            list[j] = heap.remove();
        }
    }

    public static void main(String[] args){
        Integer[] list = {-33, 88, 0, -4, 3, 555};
        heapSort(list);
        for(Integer e : list){
            System.out.println(e);
        }
    }
}