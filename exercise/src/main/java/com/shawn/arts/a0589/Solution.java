package com.shawn.arts.a0589;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 前序 根左右
 * 中序 左根右
 * 后序 左右根
 * @author ysq
 * @date 2020/8/4
 */
public class Solution {


    @Test
    public void test() {
        Node root = new Node();
        root.val = 1;
        Node ch1 = new Node();
        ch1.val = 2;
        Node ch2 = new Node();
        ch2.val = 3;
        root.children = Arrays.asList(ch1,ch2);
        Node ch21 = new Node();
        ch21.val = 4;
        ch2.children = Arrays.asList(ch1,ch21);

        List<Integer> preorder = preorder(root);
        System.out.println(preorder);
    }

    private List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        res.add(root.val);
        List<Node> children = root.children;
        if(null == children) {
            return res;
        }
        for(Node node : children) {
            if (null == node) {
                continue;
            }
            List<Integer> preorder = preorder(node);
            res.addAll(preorder);
        }
        return res;
    }

}

class Node {
    public int val;
    public List<Node> children;
    public Node(){}
    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
