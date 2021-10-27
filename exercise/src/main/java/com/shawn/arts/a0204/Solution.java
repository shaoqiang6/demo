package com.shawn.arts.a0204;

import com.alibaba.fastjson.JSON;


/**定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 反转一个单链表。

 示例:

 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL
 进阶:
 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/reverse-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ysq
 * @date 2020/8/16
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode node = new ListNode(0);
        node.next = new ListNode(1);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(4);
        System.out.println(JSON.toJSONString(node));
        ListNode reverse = solution.reverse(node);
        System.out.println(reverse);
    }

    public ListNode reverse(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
}

/**
 * Definition for singly-linked list.
 */

class ListNode {
    int value;
    ListNode next;

    ListNode(int x) {
        value = x;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
