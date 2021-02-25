package com.cn.consistencyHash;

import java.util.*;

/**
 * 虚拟节点的实现方法
 */
public class ConsistenceHash {
    //物理节点集合
    private List<String> realNodes = new ArrayList<>();
    //物理节点与虚拟节点的对应关系，存储的是虚拟节点的hash值
    private Map<String,List<Integer>> real2VirtualMap = new HashMap<>();
    //每个物理节点的虚拟节点数量
    private int virtualNums = 100;

    //排序存储结构红黑树，key是虚拟节点的hash值，value是物理节点
    private SortedMap<Integer,String> sortedMap = new TreeMap<>();

    public ConsistenceHash(int virtualNums){
        super();
        this.virtualNums = virtualNums;
    }

    public ConsistenceHash() {
    }

    //加入服务器的方法 node是真实的物理节点
    public void addServer(String node){
        this.realNodes.add(node);
        String vnode = null;
        int i = 0,count = 0;
        List<Integer> virtualNodes = new ArrayList<>();
        this.real2VirtualMap.put(node,virtualNodes);

        //创建虚拟节点，并放到环上(排序存储)
        while(count < this.virtualNums){
            i++;
            vnode = node + "&&" + i;//虚拟节点值
            int hashValue = FNV1_32_HASH.getHash(vnode);
            if(!this.sortedMap.containsKey(hashValue)){
                virtualNodes.add(hashValue);
                this.sortedMap.put(hashValue,node);
                count++;
            }
        }
    }

    //找到数据存放节点
    public String getServer(String key){
        int hash = FNV1_32_HASH.getHash(key);
        //得到大于该hash值的所有虚拟节点map
        SortedMap<Integer,String> subMap = sortedMap.tailMap(hash);
        //取第一个key
        Integer vhash = subMap.firstKey();
        //返回对应的服务器
        return subMap.get(vhash);
    }

    public static void main(String[] args){
        ConsistenceHash ch = new ConsistenceHash();
        ch.addServer("192.168.1.10");
        ch.addServer("192.168.1.11");
        ch.addServer("192.168.1.12");

        for (int i = 0;i < 10;i++){
            System.out.println("a" + i + "对应的服务器:" + ch.getServer("a"+i));
        }
    }
}
