import java.util.ArrayList;

public class Heap<E extends Comparable<E>> {
    private ArrayList<E> list = new ArrayList<>();
    public Heap(){}
    public Heap(E[] objects){
        for(int i = 0; i < objects.length; i++){
            add(objects[i]);
        }
    }

    public void add(E newObeject){
        list.add(newObeject);
        int curIndex = list.size() - 1;
        while(curIndex > 0){
            int parentIndex = (curIndex - 1) / 2;
            if(list.get(curIndex).compareTo(list.get(parentIndex)) > 0){
                E tmp = list.get(curIndex);
                list.set(curIndex, list.get(parentIndex));
                list.set(parentIndex, tmp);
            }else{
                break;
            }
            curIndex = parentIndex;
        }
    }

    public E remove(){
        if(list.size() == 0) return null;
        E removedObj = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int curIndex = 0;
        while(curIndex < list.size()){
            int leftChildIndex = 2 * curIndex + 1;
            int rightChildIndex = 2 * curIndex + 2;
            if(leftChildIndex >= list.size()) break;
            int maxIndex = leftChildIndex;
            if(rightChildIndex < list.size()){
                if(list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0){
                    maxIndex = rightChildIndex;
                }
            }

            if(list.get(curIndex).compareTo(list.get(maxIndex)) < 0){
                E tmp = list.get(maxIndex);
                list.set(maxIndex, list.get(curIndex));
                list.set(curIndex, tmp);
                curIndex = maxIndex;
            }else{
                break;
            }
        }
        return removedObj;
    }

    public int getSize(){
        return list.size();
    }
}