package redissource;

import java.util.Arrays;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashMoniExample<T> {

 //接口，能够保证可以使用各种各样的hash算法,说明JDK自带的hashcode，效果并不好
 private final HashInterface hashFunction;
 
 //复制集
 private final int numberOfReplicas;

 //可排序的键值为Int类型的Map代表那个环，T代表缓存的数据
 private final TreeMap<Integer, T> circle = new TreeMap<Integer, T>();

 public ConsistentHashMoniExample(HashInterface hashFunction, int numberOfReplicas, Collection<T> nodes) {
  this.hashFunction = hashFunction;
  this.numberOfReplicas = numberOfReplicas;
  //集群中的节点，预先设置好的
  for (T node : nodes) {
   add(node);
  }
 }

 public void add(T node) {
  //每一个节点的复制集就是节点的名称后面+数字标识
  //put里面的设置，暂时先这样，标识意思即可
  //正规的话，节点是需要均匀分布的
  for (int i = 0; i < numberOfReplicas; i++) {
   int value = hashFunction.hash(node.toString() + i);
   circle.put(value, node);
  }
 }

 public void remove(T node) {
  for (int i = 0; i < numberOfReplicas; i++) {
   circle.remove(hashFunction.hash(node.toString() + i));
  }
 }

 public T get(Object key) {
  if (circle.isEmpty()) {
   return null;
  }
  int hash = hashFunction.hash(key);
  if (!circle.containsKey(hash)) {
   //使用treeMap的一个特性，就是tailMap方法：返回比hash值大的以后的节点
   //例如存储的是{1=node1,3=node4,7=node5,9=node10} 
   //tailMap(6) 返回的是{7=node5,9=node10}
   //可以说是，看到了这个方法，让我想起来这个一致性的hash算法的模拟
   SortedMap<Integer, T> tailMap = circle.tailMap(hash);
   hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
  }
  return circle.get(hash);
 }

 public static void main(String[] args) {
  Collection<String> nodes = Arrays.asList("node1", "node2", "node3", "node4");
  ConsistentHashMoniExample<String> redisHashing = new ConsistentHashMoniExample<String>(new HashFunctionImpl(), 3,nodes);
  System.out.println(redisHashing.get("1"));
 }
}

interface HashInterface {

 Integer hash(String key);

 int hash(Object key);

}

class HashFunctionImpl implements HashInterface {

 @Override
 public Integer hash(String key) {
  return Math.abs(key.hashCode()) % 100;
 }

 @Override
 public int hash(Object key) {
  return Math.abs(key.hashCode()) % 100;
 }

}
