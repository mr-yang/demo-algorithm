package com.example.algorithm.middling;

import com.example.algorithm.bean.ListNode;

/**
 * @author tianxiaoyang
 * @date 2020-12-16 11:14
 * @describe 环形链表，https://leetcode-cn.com/problems/linked-list-cycle-ii
 */
public class AnnularList {
    //第二种，快慢指针算法，时间复杂度O(n)占用空间O(n)
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        //快慢指针，记住结果，3个指针，fast快读，slow慢速，ptr慢速
        //fast每次走2步，slow每次走一步，如果fast和slow相等，这时ptr也可以从head开始走1步，
        //如果ptr和slow相等就说明有环了
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        node4.next = node2;
        AnnularList annularList = new AnnularList();
        ListNode listNode = annularList.detectCycle(node1);
        if(listNode == null){
            System.out.println("listNode = -1");
        }else{
            System.out.println("listNode = " +  listNode.val);
        }

    }


}
