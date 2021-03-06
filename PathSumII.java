/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
*/

// O(n), O(klogn) space cost, k is the number of results we found

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
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null) {
            return result;
        }
        ArrayList<Integer> path = new ArrayList<Integer>();
        helper(root, sum, result, path);
        return result;
    }
    
    private void helper(TreeNode root, int sum, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> path) {
        // !!! don't forget to check null !!!
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null && root.val == sum) {
            path.add(root.val);
            // !!! have to use new, because arraylist is an object !!!
            result.add(new ArrayList<Integer>(path));
            // !!! add remove each time !!!
            path.remove(path.size() - 1);
            return;
        }
        if(root.left != null) {
            path.add(root.val);
            helper(root.left, sum - root.val, result, path);
            path.remove(path.size() - 1);
        }
        if(root.right != null) {
            path.add(root.val);
            helper(root.right, sum - root.val, result, path);
            path.remove(path.size() - 1);
        }
    }
}


/********* old version *********/

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
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        helper(root,sum,result,path);
        return result;
    }
    
    void helper(TreeNode root, int sum, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> path){
        if(root==null) return;
        // find suitable leaf node
        if(root.val==sum && root.left==null && root.right==null){
            path.add(root.val);
            ArrayList<Integer> temp = new ArrayList<Integer>(path);
            result.add(temp);
            path.remove(path.size()-1);  //backtrack
            return;
        }
        // backtrack dfs
        path.add(root.val);
        helper(root.left,sum-root.val,result,path);
        helper(root.right,sum-root.val,result,path);
        path.remove(path.size()-1);  //backtrack
    }
}
