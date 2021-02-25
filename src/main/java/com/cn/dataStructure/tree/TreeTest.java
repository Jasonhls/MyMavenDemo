package com.cn.dataStructure.tree;

public class TreeTest {
    public static void main(String[] args) {
        Tree t = new Tree();
        t.insert(12L);
        t.insert(35L);
        t.insert(0L);
        t.insert(5L);
        t.insert(13L);
        t.insert(100L);
        t.insert(-1L);

        t.printTree(t);
        System.out.println("------------------");
        TreeNode treeNode = t.find(4);
        if(treeNode == null){
            System.out.println("查询出来的数据为空");
        }else{
            System.out.println("查找出来的数据：" + treeNode.data);
        }
        t.delete(12);
        t.printTree(t);
    }
}
