package com.cn.dataStructure.hashtable;

public class HashTable {
    private LinkList[] arr;

    public HashTable(){
        this.arr = new LinkList[100];
    }

    public HashTable(int maxSize){
        this.arr = new LinkList[maxSize];
    }

    /**
     * 插入数据
     * @param info
     */
    public void insert(Info info){
        String key = info.getKey();
        int hashVal = key.hashCode();
        if(arr[hashVal] == null){
            arr[hashVal] = new LinkList();
        }
        arr[hashVal].insertFirst(info);
    }

    /**
     * 查找数据
     * @param key
     * @return
     */
    public Info find(String key){
        int hashVal = key.hashCode();
        if(hashVal >= 0 && hashVal < arr.length){
            LinkList linkList = arr[hashVal];
            if(linkList == null){
                return null;
            }
            Node node = linkList.getNode(key);
            return node == null ? null : node.info;
        }else{
            return null;
        }
    }

    /**
     * 删除数据
     * @param key
     * @return
     */
    public Info delete(String key){
        int hashVal = key.hashCode();
        if(hashVal >= 0 && hashVal < arr.length){
            LinkList linkList = arr[hashVal];
            if(linkList == null){
                return null;
            }
            Node node = linkList.deleteNode(key);
            return node == null ? null : node.info;
        }else{
            return null;
        }
    }
}
