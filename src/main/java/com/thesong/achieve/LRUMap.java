package com.thesong.achieve;

import java.util.HashMap;

/**
 * @Author thesong
 * @Date 2020/12/8 16:53
 * @Version 1.0
 * @Describe
 */

public class LRUMap<K, V> {

    // 节点 class
    class Node<K, V> {
        public Node<K, V> pre;
        public Node<K, V> next;
        public K key;
        public V value;

        public Node(K key, V value) {
            this.value = value;
            this.key = key;
        }
    }

    private Node<K, V> head;
    private Node<K, V> tail;
    private int limit;
    private HashMap<K, Node<K, V>> hashMap;

    public LRUMap(int limit) {
        this.limit = limit;
        this.hashMap = new HashMap<>();
    }

    private void addNode(Node<K, V> node) {
        if (tail != null) {
            tail.next = node;
            node.pre = tail;
            node.next = null;
        }
        tail = node;
        if (head == null) {
            head = node;
        }
    }

    private K removeNode(Node<K, V> node) {
        if (node == tail) {
            tail = node.pre;
        } else if (node == head) {
            head = node.next;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    private void refreshNode(Node<K, V> node) {
        if (node == tail) {
            return;
        }
        removeNode(node);
        addNode(node);
    }

    public void put(K k, V value) {
        Node<K, V> node1 = hashMap.get(k);
        if (node1 == null) {
            if (hashMap.size() > limit) {
                K oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            Node<K, V> node = new Node<>(k, value);
            addNode(node);
            hashMap.put(k, node);
        } else {
            node1.value = value;
            refreshNode(node1);
        }
    }

    public void remove(K k) {
        Node<K, V> node = hashMap.get(k);
        removeNode(node);
        hashMap.remove(k);
    }

    public V get(K k) {
        Node<K, V> node = hashMap.get(k);
        if (node == null) {
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    public static void main(String[] args) {
        LRUMap<String, String> map = new LRUMap<>(5);
        for (Integer i = 0; i < 10; i++) {
            map.put(i.toString(), "test" + i);
            System.out.println(map.hashMap.size());
        }
    }


}
