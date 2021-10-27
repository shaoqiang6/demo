package com.shawn.arts.a0104;

import org.junit.Test;

/**
 * @author ysq
 * @date 2020/8/5
 */
public class Solution {

    @Test
    public void test() {
        TreeNode node1 = new TreeNode(11);
        TreeNode node0 = new TreeNode(10);
        TreeNode node2 = new TreeNode(13);
        TreeNode node3 = new TreeNode(23);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
//        TreeNode node15 = new TreeNode(15);
//        TreeNode node25 = new TreeNode(25);
        TreeNode node35 = new TreeNode(35);
        node1.right = node2;
        node1.left = node0;
        node2.right = node3;
        node3.right = node35;
        node0.left = node4;
        node4.right = node5;
        System.out.println(maxDepth(node1));
    }

    private int maxDepth(TreeNode root) {
        // null 为0 否则为1
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}