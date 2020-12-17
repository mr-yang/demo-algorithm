package com.example.algorithm.middling;

import java.util.HashMap;

/**
 * @author tianxiaoyang
 * @date 2020-12-16 16:54
 * @describe LRU缓存机制
 * HashMap + 双向链表，HashMap解决查找为O(1)，双向链表解决增删改为O(1)
 */
public class LRUCache {

    private final int capacity;
    private int size;
    private HashMap<Integer,Node> map;
    private Node head;
    private Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
        head = new Node();
        tail = new Node();
        //构造2个站位头节点和尾节点
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node == null){
            return -1;
        }
        //读取了这个值也需要把这个节点移动到双向链表的链表头
        moveHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        //之前没有存储过
        if(node == null){
            Node newNode = new Node(key, value);
            //最新的放到双向链表头部，
            addHead(newNode);
            map.put(key,newNode);
            ++size;
            //判断是否已经超过了最大容量，如果超过最大容量删除尾节点
            if(size>capacity){
                Node tailNode = removeTail();
                map.remove(tailNode.key);
                --size;
            }
        }else{
            node.value = value;
            //重新设置了需要把这个节点移动到双向链表的链表头
            moveHead(node);
        }
    }

    private void moveHead(Node node) {
        //断开之前的连接
        Node cPre = node.pre;
        Node cNext = node.next;
        cPre.next = cNext;
        cNext.pre = cPre;
        //把这个节点放到头节点
        addHead(node);
    }

    private Node removeTail() {
        //删除尾节点，其实就是把最后一个节点前后节点互相连接就可以了
        Node tPre = tail.pre;
        tPre.pre.next = tail;
        tail.pre = tPre.pre;
        return tPre;
    }

    private void addHead(Node node) {
        //添加到头节点，其实就把当前节点指向之前头节点后边的界面，当前节点指向头节点
        Node hNext = head.next;
        node.next = hNext;
        hNext.pre = node;
        head.next = node;
        node.pre = head;
    }

    class Node{
        public int key;
        public int value;
        public Node next;
        public Node pre;
        public Node() {
        }
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println("lRUCache = " + lRUCache.get(1));//返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println("lRUCache = " + lRUCache.get(2));//返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println("lRUCache = " + lRUCache.get(1));// 返回 -1 (未找到)
        System.out.println("lRUCache = " + lRUCache.get(3));// 返回 3
        System.out.println("lRUCache = " + lRUCache.get(4));// 返回 4

    }
}
