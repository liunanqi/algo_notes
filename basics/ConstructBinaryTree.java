import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class ConstructBinaryTree {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args){
        System.out.print("Hello World!");
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post){
        return null;
    }

    public TreeNode constructFromPreIn(int[] pre, int[] post){
        return null;
    }

    int preOrderCount = 0;
    public TreeNode bstFromPreOrder(int[] preOrder){
        if(preOrder == null) return null;
        return bstFromPreOrder(preOrder, Integer.MAX_VALUE);
    }

    private TreeNode bstFromPreOrder(int[] preOrder, int bound){
        if(preOrderCount == 0 || preOrder[preOrderCount] > bound) return null;
        TreeNode node = new TreeNode(preOrder[preOrderCount++]);
        node.left = bstFromPreOrder(preOrder, node.val);
        node.right = bstFromPreOrder(preOrder, bound);
        return node;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) return t2;
        if(t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    public TreeNode mergeTreesIteratively(TreeNode t1, TreeNode t2){
        if(t1 == null) return t2;
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{t1, t2});
        //本题将t2合并到t1中
        while(!stack.isEmpty()){
            TreeNode[] t = stack.pop();
            if(t[0] == null || t[1] == null){
                continue;
            }

            t[0].val += t[1].val;

            if(t[0].left == null){
                t[0].left = t[1].left;
            }else{
                stack.push(new TreeNode[]{t[0].left, t[1].left});
            }

            if(t[0].right == null){
                t[0].right = t[1].right;
            }else{
                stack.push(new TreeNode[]{t[0].right, t[1].right});
            }
        }
        return t1;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) {
            return null;
        }
        TreeNode head = helper(nums, 0, nums.length - 1);
        return head;
    }

    private TreeNode helper(int[] nums, int low, int high) {
        if(low > high) {
            return null;
        }
        int mid = low + (high - low) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, low, mid - 1);
        node.right = helper(nums, mid + 1, high);
        return node;
    }

    public TreeNode sortedArrayToBSTIterative(int[] nums) {
        int len = nums.length;
        if ( len == 0 ) { return null; }

        // 0 as a placeholder
        TreeNode head = new TreeNode(0);

        Deque<TreeNode> nodeStack       = new LinkedList<>() {{ push(head);  }};
        Deque<Integer>  leftIndexStack  = new LinkedList<>()  {{ push(0);     }};
        Deque<Integer>  rightIndexStack = new LinkedList<>()  {{ push(len-1); }};

        while ( !nodeStack.isEmpty() ) {
            TreeNode currNode = nodeStack.pop();
            int left  = leftIndexStack.pop();
            int right = rightIndexStack.pop();
            int mid   = left + (right-left)/2; // avoid overflow
            currNode.val = nums[mid];
            if ( left <= mid-1 ) {
                currNode.left = new TreeNode(0);
                nodeStack.push(currNode.left);
                leftIndexStack.push(left);
                rightIndexStack.push(mid-1);
            }
            if ( mid+1 <= right ) {
                currNode.right = new TreeNode(0);
                nodeStack.push(currNode.right);
                leftIndexStack.push(mid+1);
                rightIndexStack.push(right);
            }
        }
        return head;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) {
            return null;
        }

        ListNode mid = findTheMiddleElement(head);
        TreeNode node = new TreeNode(mid.val);

        if(head == mid) {
            return node;
        }

        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);
        return node;
    }

    //常规操作：使用快慢指针查找List中间节点
    private ListNode findTheMiddleElement(ListNode head) {
        ListNode prevNode = null;
        ListNode slowNode = head;
        ListNode fastNode = head;

        while(fastNode != null && fastNode.next != null) {
            prevNode = slowNode;
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        if(prevNode != null) {
            prevNode.next = null;
        }
        return slowNode;
    }
}
