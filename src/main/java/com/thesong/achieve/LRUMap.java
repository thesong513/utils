package com.thesong.achieve;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author thesong
 * @Date 2020/12/8 19:52
 * @Version 1.0
 * @Describe
 */
public class LRUMap<K, V> {
    class Node<K, V> {
        public Node<K, V> pre;
        public Node<K, V> next;
        public K key;
        public V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private int limit;
    private Map<K,Node<K,V>> map = new HashMap<K,Node<K,V>>();
    private Node head;
    private Node tail;

    public LRUMap(int limit){
        this.limit = limit;
        this.map = new HashMap<K,Node<K,V>>();
    }

    private void addNode(Node<K,V> node){
        if(tail!=null){
            tail.next = node;
            node.pre = tail;
            node.next = null;
        }
        tail = node;
        if(head==null){
            head=node;
        }
    }

    private void removeNode(Node<K,V> node){
        if(node==tail){
            tail = tail.pre;
        }else if(node==head){
            head = node.next;
        }else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
    }

    private void refreshNode(Node<K,V> node){
        if(node==tail){
            return;
        }
        removeNode(node);
        addNode(node);
    }

    public void put(K key,V value){
        Node<K, V> node = map.get(key);
        if(node==null){
            if(map.size()>=limit){
                map.remove(head.key);
                removeNode(head);
            }
            node = new Node<K, V>(key, value);
            addNode(node);
            map.put(key, node);
        } else {
            node.value = value;
            refreshNode(node);
        }
    }

    public void remove(K key){
        Node<K, V> node = map.get(key);
        removeNode(node);
        map.remove(key);
    }

    public V get(K key){
        Node<K, V> node = map.get(key);
        if(node==null){
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    public int size(){
        return map.size();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        Node node = head;
        while (node!=null){
            String nodeStr = node.key+":"+node.value+"->";
            builder.append(nodeStr);
            node = node.next;
        }
        return builder.toString();
    }


}
