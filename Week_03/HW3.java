package com.yim.jike;

import java.util.HashMap;

public class HW3 {
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int _val) {
            val = _val;
        }

        public TreeNode(int _val, TreeNode l, TreeNode r) {
            val = _val;
            left = l;
            right = r;
        }
    }

    public static void main(String[] args) {
//        lowestCommonAncestor_main();
        buildTree_main();
    }


    //<editor-fold desc="lowestCommonAncestor">
    static int pre_idx = 0;
    static int[] preorder;
    static int[] inorder;
    static HashMap<Integer, Integer> idx_map = new HashMap<>();

    private static void buildTree_main() {
        int[] pre = new int[]{3, 9, 20, 15, 7};
        int[] in = new int[]{9, 3, 15, 20, 7};
        TreeNode treeNode = buildTree(pre, in);
        eachTree(treeNode);
    }

    private static void eachTree(TreeNode treeNode) {
        if (treeNode == null) return;
        p(treeNode.val);
        eachTree(treeNode.left);
        eachTree(treeNode.right);
    }

    private static TreeNode buildTree(int[] pre, int[] in) {
        preorder = pre;
        inorder = in;
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper(0, inorder.length);
    }

    private static TreeNode helper(int in_left, int in_right) {
        if (in_left == in_right)
            return null;

        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);
        int index = idx_map.get(root_val);
        pre_idx++;
        root.left = helper(in_left, index);
        root.right = helper(index + 1, in_right);
        return root;
    }

    //</editor-fold>
    //<editor-fold desc="lowestCommonAncestor">
    private static TreeNode rst = null;

    private static void lowestCommonAncestor_main() {
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node4 = new TreeNode(4, null, null);

        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node2 = new TreeNode(2, node7, node4);
        TreeNode node0 = new TreeNode(0, null, null);
        TreeNode node8 = new TreeNode(8, null, null);

        TreeNode node5 = new TreeNode(5, node6, node2);
        TreeNode node1 = new TreeNode(1, node0, node8);

        TreeNode root = new TreeNode(3, node5, node1);

        lowestCommonAncestor(root, node5, node1);
        p(String.valueOf(rst.val));
    }

    private static void lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
    }

    private static boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            rst = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    //</editor-fold>

    private static void p(Object param) {
        p(String.valueOf(param));
    }

    private static void p(String param) {
        System.out.println("rtn:" + param);
    }
}
