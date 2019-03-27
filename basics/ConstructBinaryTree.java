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

    public static void main(String[] args){

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

    public TreeNode bstFromPreOrder(int[] preOrder, int bound){
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
}