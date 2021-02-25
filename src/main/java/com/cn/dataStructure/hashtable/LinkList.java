package com.cn.dataStructure.hashtable;

public class LinkList {
    //头节点
    private Node first;

    public LinkList(){
        first = null;
    }

    /**
     * 插入一个节点，在头节点前插入
     * @param info
     */
    public void insertFirst(Info info){
        Node node = new Node(info);
        node.next = first;
        first = node;
    }

    /**
     * 删除头节点
     * @return
     */
    public Node deleteFirst(){
        Node node = first;
        first = node.next;
        return node;
    }

    /**
     * 显示方法
     */
    public void display(){
        Node current = first;
        while(current != null){
            current.display();
            current = current.next;
        }
    }

    /**
     * 根据key获取节点
     * @param key
     * @return
     */
    public Node getNode(String key){
        Node current = first;
        while(!key.equals(current.info.getKey())){
            if(current.next == null){
                return null;
            }
            current = current.next;
        }
        return current;
    }

    /**
     * 删除数据
     * @param key
     * @return
     */
    public Node deleteNode(String key){
        Node current = first;
        Node last = null;
        while(!key.equals(current.info.getKey())){
            if(current.next == null){
                return null;//表示该链表中没有该数据
            }
            last = current;
            current = current.next;
        }
        if(last == null){
            last = first;
            first = first.next;
            return last;
        }else{
            last.next = current.next;
            return current;
        }
    }
}
