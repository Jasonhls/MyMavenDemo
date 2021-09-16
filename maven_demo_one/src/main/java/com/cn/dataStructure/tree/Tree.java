package com.cn.dataStructure.tree;

/**
 * 二叉树
 */
public class Tree {
    /*根节点*/
    private TreeNode root;

    /**
     * 插入
     * @param data
     */
    public void insert(long data){
        TreeNode treeNode = new TreeNode(data);
        if(root == null){
            root = treeNode;
            return;
        }else{
//            compare(treeNode,root);//方法一
            /*方法二*/
            TreeNode current = root;
            TreeNode parent;
            while(true){
                parent = current;
                if(treeNode.data <= current.data){
                    current = current.leftTreeNode;
                    if(current == null){
                        parent.leftTreeNode = treeNode;
                        return;
                    }
                }else{
                    current = current.rightTreeNode;
                    if(current == null){
                        parent.rightTreeNode = treeNode;
                        return;
                    }
                }
            }
        }
    }

    public void compare(TreeNode treeNode, TreeNode parentTreeNode){
        if(treeNode.data <= parentTreeNode.data){
            TreeNode leftTreeNode = parentTreeNode.leftTreeNode;
            if(leftTreeNode == null){
                parentTreeNode.leftTreeNode = treeNode;
            }else{
                compare(treeNode, leftTreeNode);
            }
        }else{
            TreeNode rightTreeNode = parentTreeNode.rightTreeNode;
            if(rightTreeNode == null){
                parentTreeNode.rightTreeNode = treeNode;
            }else{
                compare(treeNode, rightTreeNode);
            }
        }
    }

    /**
     * 查找
     * @param data
     * @return
     */
    public TreeNode find(long data){
        if(root == null){
            return null;
        }else{
            /*方法一*/
            /*if(data == root.data){
                return root;
            }else{
                return get(data,root);
            }*/
            /*方法二*/
            TreeNode current = root;
            while(data != current.data){
                if(data < current.data){
                    current = current.leftTreeNode;
                }else{
                    current = current.rightTreeNode;
                }
                if(current == null){
                    return null;
                }
            }
            return current;
        }
    }

    public TreeNode get(long data, TreeNode treeNode){
        if(treeNode == null){
            return null;
        }else{
            if(data == treeNode.data){
                return treeNode;
            } else if(data < treeNode.data){
                return get(data, treeNode.leftTreeNode);
            }else {
                return get(data, treeNode.rightTreeNode);
            }
        }
    }

    /**
     * 删除
     * @param data
     */
    public boolean delete(long data){
        if(root == null){
            return false;
        }else{
            /*if(data == root.data){
                root = null;
            } else if(data < root.data){
                del(data,root,root.leftTreeNode);
            }else{
                del(data,root,root.rightTreeNode);
            }*/
            if(data == root.data){
                root = null;
                return true;
            }
            TreeNode current = root;
            TreeNode parent = current;
            boolean isLeftNode = true;
            //查找data对应的节点current,父节点parent,是否为左子节点isLeftNode
            while(data != current.data){
                parent = current;
                if(data < current.data){
                    current = current.leftTreeNode;
                    isLeftNode = true;
                }else{
                    current = current.rightTreeNode;
                    isLeftNode = false;
                }
                if(current == null){
                    return false;
                }
            }
            //开始删除操作
            if(current.leftTreeNode == null && current.rightTreeNode == null) {//如果为叶子节点
                if (isLeftNode) {
                    parent.leftTreeNode = null;
                } else {
                    parent.rightTreeNode = null;
                }
            }else if(current.leftTreeNode != null && current.rightTreeNode == null){
                if(isLeftNode){
                    parent.leftTreeNode = current.leftTreeNode;
                }else{
                    parent.rightTreeNode = current.leftTreeNode;
                }
            }else if(current.leftTreeNode == null && current.rightTreeNode != null){
                if(isLeftNode){
                    parent.leftTreeNode = current.rightTreeNode;
                }else{
                    parent.rightTreeNode = current.rightTreeNode;
                }
            }else{//要删除的节点有左子节点和右子节点
                //找出可以替代删除节点的节点(值最靠近这个节点的有两个)
                //一个是刚小于删除节点的值，一个是刚大于这个节点的值，getSuccessor方法取得是刚大于这个节点的
                TreeNode successor = getSuccessor(current);
                successor.leftTreeNode = current.leftTreeNode;
                if(isLeftNode){
                    parent.leftTreeNode = successor;
                }else{
                    parent.rightTreeNode = successor;
                }
            }
            return true;
        }
    }

    public TreeNode getSuccessor(TreeNode delTreeNode){
        //successor：需要替换删除节点所在位置的节点
        //parent：successor节点的父节点
        TreeNode parent = delTreeNode;
        TreeNode successor = delTreeNode;
        TreeNode current = delTreeNode.rightTreeNode;
        while(current != null){
            parent = successor;
            successor = current;
            current = current.leftTreeNode;
        }
        if(successor != delTreeNode.rightTreeNode){
            //由于successor移动到要删除节点所在位置，因此successor的位置由它的右节点代替
            parent.leftTreeNode = successor.rightTreeNode;//这里不管successor的右节点是否为空
            successor.rightTreeNode = delTreeNode.rightTreeNode;
        }
        return successor;
    }

//    public void del(long data,TreeNode parentNode,TreeNode childNode){
//        if(childNode == null){
//            return;
//        }else {
//            if(childNode.data <= parentNode.data){//说明childNode为左子节点
//                if(data == childNode.data){
//                    parentNode.leftTreeNode = null;
//                }else if(data < childNode.data){
//                   del(data,childNode,childNode.leftTreeNode);
//                }else {
//                    del(data,childNode,childNode.rightTreeNode);
//                }
//            }else{//否则为右子节点
//                if(data == childNode.data){
//                    parentNode.rightTreeNode = null;
//                }else if(data < childNode.data){
//                    del(data,childNode,childNode.leftTreeNode);
//                }else{
//                    del(data,childNode,childNode.rightTreeNode);
//                }
//            }
//        }
//    }

    /**
     * 树遍历方法
     * @param t
     */
    public void printTree(Tree t){
        if(t == null){
            return;
        }else{
            /*中序遍历*/
            print(t.root);
        }
    }

    public void print(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        print(treeNode.leftTreeNode);
        System.out.println(treeNode.data);
        print(treeNode.rightTreeNode);
    }
}
