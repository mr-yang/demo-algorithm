package com.example.algorithm.simple;

import com.example.algorithm.bean.ListNode;

/**
 * @author tianxiaoyang
 * @date 2020-12-16 09:40
 * @describe 链表反转，https://leetcode-cn.com/problems/reverse-linked-list
 */
public class ReverseList206 {
    //反转链表
    public ListNode reverseList(ListNode head) {
        //记录上一个节点，默认为null，因为单链表的最后一个节点的下一个节点为null
        ListNode pre = null;
        //记录当前节点
        ListNode cur = head;
        //遍历节目反转，当前节点为null停止
        while (cur != null) {
            //记录当前节点的下一个界面
            ListNode next = cur.next;
            //把当前节点的下一个节目指向上一个节点
            cur.next = pre;
            //把当前节点赋值给上一个节点，为了下次继续赋值给当前节点的上一个节点
            pre = cur;
            //把当前节点的下一个节点赋值给当前节点，这样就能反向指向了
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next= node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println("原始链表");
        println(node1);
        ReverseList206 reverseList = new ReverseList206();
        ListNode listNode = reverseList.reverseList(node1);
        System.out.println();
        System.out.println("反转之后的链表");
        println(listNode);
    }

    private static void println(ListNode listNode) {
        while (listNode!= null){
            System.out.print(listNode.val +"->");
            listNode = listNode.next;
        }
    }

}
