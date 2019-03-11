import java.util.ArrayList;
import java.util.List;

public class PalindromeList {
    public static void main(String[] args){
        PalindromeList list = new PalindromeList();
        ListNode head = list.createNode(-129);
        ListNode second = list.createNode(-129);
        head.next = second;
        System.out.println(isPalindrome(head));
    }


    public static boolean isPalindrome(ListNode head){
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast != null){
            slow = slow.next;
        }

        slow = reverse(slow);
        fast = head;
        while(slow != null){
            if(slow.val != fast.val){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }

//        List<Integer> nodeList = new ArrayList<>();
//        while(head != null){
//            nodeList.add(head.val);
//            head = head.next;
//        }
//        int len = nodeList.size();
//        for(int i = 0; i < len / 2; i++){
//            if(!nodeList.get(i).equals(nodeList.get(len - i - 1))){
//                return false;
//            }
//        }
        return true;
    }

    public static ListNode reverse(ListNode head){
        ListNode tmp = null;
        while(head != null){
            ListNode next = head.next;
            head.next = tmp;
            tmp = head;
            head = next;
        }
        return tmp;
    }

    private ListNode createNode() {
        return new ListNode();
    }

    private ListNode createNode(int x) {
        return new ListNode(x);
    }

    private class ListNode{
        int val;
        ListNode next;
        ListNode() { val = 0; }
        ListNode(int x){
            val = x;
        }
    }
}