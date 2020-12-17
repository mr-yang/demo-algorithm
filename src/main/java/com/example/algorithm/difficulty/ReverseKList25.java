package com.example.algorithm.difficulty;

import com.example.algorithm.bean.ListNode;

import java.util.ArrayList;

/**
 * @author tianxiaoyang
 * @date 2020-12-17 10:08
 * @describe k个一组翻转链表，https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
 */
public class ReverseKList25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        //占位节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //pre为待翻转k个一组链表的前驱节点
        ListNode pre = dummy;
        //end为待翻转k个一组链表的末尾节点
        ListNode end = dummy;
        while(end.next != null){
            //循环K次是为了跳到待翻转链表的末尾，end != null是为了最后的链表不够K个的情况
            for (int i = 0; i < k && end != null ; i++) {
                end = end.next;
            }
            //end = null说明最后的链表不够K个，直接跳出循环
            if(end == null){
                break;
            }
            //start为待翻转k个一组链表的起始节点
            ListNode start = pre.next;
            //next为待翻转k个一组链表的后继节点
            ListNode next = end.next;
            //翻转之前需要先断开后边的节点，不然会对翻转链表有影响
            end.next = null;
            //记录好之后去翻转链表
            ListNode listNode = reverseList(start);
            //翻转之后连接，pre连接翻转链表的头节点
            pre.next = listNode;
            //翻转之后start节点就变成了未节点，连接上后面未翻转的界面
            start.next = next;
            //连接好之后修正pre和end，是pre和end指向待翻转链表的前驱节点
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    //翻转链表
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode nNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nNode;
        }
        return pre;
    }


    public static void main(String[] args) {
        ArrayList<ListNode> listNodes = new ArrayList<ListNode>();
        int length = 8;
        for (int i = 0; i < length; i++) {
            ListNode listNode = new ListNode(i + 1);
            if(i != 0){
                listNode.next = listNodes.get(i-1);
            }
            listNodes.add(listNode);
        }
        ReverseKList25 reverseKList = new ReverseKList25();
        ListNode listNode = reverseKList.reverseList(listNodes.get(length - 1));
        System.out.println("原始链表");
        println(listNode);
        int k = 4;
        ListNode reverseKGroup = reverseKList.reverseKGroup(listNode, k);
        System.out.println();
        System.out.println(k+"个一组翻转之后的链表");
        println(reverseKGroup);
    }

    private static void println(ListNode listNode) {
        while (listNode!= null){
            System.out.print(listNode.val +"->");
            listNode = listNode.next;
        }
    }
}
