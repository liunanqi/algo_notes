import java.util.*;

/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}*/

public class TreePrinter {
    public int[][] printTree(TreeNode root) {
        // write code here
        ArrayList<ArrayList<TreeNode>> res = new  ArrayList<>();
        ArrayList<TreeNode> level = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        TreeNode node = null;
        TreeNode last = root;
        TreeNode nlast = last;
        while(!queue.isEmpty()){
            node = queue.poll();
            level.add(node);
            
            if(node.left != null) {
                queue.offer(node.left);
                nlast = node.left;
            }
            
            if(node.right != null){
                queue.offer(node.right);
                nlast = node.right;
            }
            
            if(node == last){
                last = nlast;
                res.add(level);
                level = new ArrayList();
            }
        }
        
        int[][] result = new int[res.size()][];
        for(int i = 0; i < res.size(); i++){
            result[i] = new int[res.get(i).size()];
            for(int j = 0; j < result[i].length; j++){
                result[i][j] = res.get(i).get(j).val;
            }
        }
        return result;
    }
}