import java.util.*;

public class BinaryTreeTraversal {

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

    public void preOrder(TreeNode root){
        if(root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

    public void preOrderIterative(TreeNode root){

    }

    public void inOrder(TreeNode root){
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public void inOrderIterative(TreeNode root){

    }

    public void postOrder(TreeNode root){
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    public void postOrderIterative(TreeNode root){

    }

    public List<List<Integer>> levelOrder(TreeNode root){
        if(root == null) return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        while(!nodes.isEmpty()){
            int count = nodes.size();
            ArrayList<Integer> level = new ArrayList<>();
            while(count > 0){
                TreeNode node = nodes.poll();
                level.add(node.val);
                if(root.left != null){
                    nodes.offer(root.left);
                }
                if(root.right != null){
                    nodes.offer(root.right);
                }
                count--;
            }
            result.add(level);
        }
        return result;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> wrapList = new LinkedList<>();

        if(root == null) return wrapList;

        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<>();
            for(int i=0; i<levelNum; i++) {
                TreeNode node = queue.poll();
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                subList.add(node.val);
            }
            wrapList.add(0, subList);
        }
        return wrapList;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        if(root == null) return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        int level = 0;

        while(!nodes.isEmpty()){
            int size = nodes.size();
            LinkedList<Integer> list = new LinkedList<>();
            for(int i = 0; i < size; i++){
                TreeNode node = nodes.poll();
                if(level % 2 == 0){
                    list.add(node.val);
                }else{
                    list.addFirst(node.val);
                }
                if(node.left != null){
                    nodes.offer(node.left);
                }
                if(node.right != null){
                    nodes.offer(node.right);
                }
            }
            level++;
            result.add(list);
        }
        return result;
    }

    public List<Integer> rightSideView(TreeNode root){
        if(root == null) return Collections.emptyList();
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        while(!nodes.isEmpty()){
            int size = nodes.size();
            for(int i = 0; i < size; i++){
                TreeNode node = nodes.poll();
                if(node.left != null) {
                    nodes.offer(node.left);
                }
                if(node.right != null) {
                    nodes.offer(node.right);
                }
                if(i == size - 1){
                    result.add(node.val);
                }
            }
        }
        return result;
    }

    private TreeNode ans = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        recurseTree(root, p, q);
        return this.ans;
    }

    public boolean recurseTree(TreeNode curNode, TreeNode p, TreeNode q){
        if(curNode == null) return false;
        int left = this.recurseTree(curNode.left, p, q)? 1 : 0;
        int right = this.recurseTree(curNode.right, p, q) ? 1 : 0;
        int mid = (curNode == p || curNode == q) ? 1 : 0;
        if((left + right + mid) >= 2){
            this.ans = curNode;
        }
        return (left + right + mid) > 0;
    }

    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {

        return root;
    }

    public TreeNode lowestCommonAncestorInBST(TreeNode root, TreeNode p, TreeNode q) {
        int parentValue = root.val;
        int pValue = p.val;
        int qValue = q.val;
        if(pValue > parentValue && qValue > parentValue){
            return lowestCommonAncestorInBST(root.right, p, q);
        }else if(pValue < parentValue && qValue < parentValue){
            return lowestCommonAncestorInBST(root.left, p, q);
        }else{
            return root;
        }
    }

    public TreeNode lowestCommonAncestorInBSTIterative(TreeNode root, TreeNode p, TreeNode q) {
        int pValue = p.val;
        int qValue = q.val;
        TreeNode node = root;
        while(node != null){
            int parentValue = node.val;

            if(pValue > parentValue && qValue > parentValue){
                node = node.right;
            }else if(pValue < parentValue && qValue < parentValue){
                node = node.left;
            }else{
                return node;
            }
        }
        return null;
    }

}
