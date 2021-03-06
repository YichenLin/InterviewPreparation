/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
*/

// recursion:

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        helper(root, result);
        return result;
    }
    
    private void helper(TreeNode root, List<Integer> result) {
        if(root == null) {
            return;
        }
        helper(root.left, result);
        helper(root.right, result);
        result.add(root.val);
    }
}

// iteration:

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Postorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        // need an extra pre node to store the previous node we visited
        TreeNode pre = null;
        while(root != null || !stack.isEmpty()) {
            if(root != null) {
                stack.push(root);
                root = root.left;
            } else {
                // if root == null:
                // peek the top of the stack
                TreeNode peek = stack.peek();
                // if the peek has right child AND it is not the previous one
                if(peek.right != null && pre != peek.right) {
                    // continue visit the right child
                    root = peek.right;
                } else {
                    // otherwise, record the peek to the result
                    result.add(peek.val);
                    // update the peek and pop the peek out
                    pre = peek;
                    stack.pop();
                }
            }
        }
        return result;
    }
}

// Another thought (more complicated)

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        TreeNode pre = null;
        while(!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            // going down
            if(pre == null || pre.left == cur || pre.right == cur) {
                // push child nodes of the current node into stack
                if(cur.left != null) {
                    stack.push(cur.left);
                } else if(cur.right != null) {
                    stack.push(cur.right);
                } else {
                    // if this is the leaf node, add to result and pop out
                    result.add(cur.val);
                    stack.pop();
                }
            } else if(cur.left == pre && cur.right != null) {
                // going up, and the previous node has right child
                stack.push(cur.right);
            } else {
                // going up, and the node has no child
                result.add(cur.val);
                stack.pop();
            }
            // previous node is the one that currently watching
            pre = cur;
        }
        return result;
    }
}


// iteration (modified the input data) : 

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root==null){
            return result;
        }
        Stack<TreeNode> helper = new Stack<TreeNode>();
        helper.add(root);
        while(!helper.empty()){
            TreeNode cur = helper.peek();
            // leaf node
            if(cur.left==null && cur.right==null){
                result.add(cur.val);
                helper.pop();
            }
            // node with left child
            if(cur.left!=null){
                helper.add(cur.left);
                cur.left = null;
                continue;
            }
            // node with right child
            if(cur.right!=null){
                helper.add(cur.right);
                cur.right = null;
                continue;
            }
        }
        return result;
    }
}
