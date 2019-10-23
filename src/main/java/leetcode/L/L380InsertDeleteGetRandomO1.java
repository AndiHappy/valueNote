package leetcode.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * @author guizhai
 *
 */
public class L380InsertDeleteGetRandomO1 {

 /**
  
 Design a data structure that supports all following operations in average O(1) time.
 
 insert(val): Inserts an item val to the set if not already present.
 remove(val): Removes an item val from the set if present.
 getRandom: Returns a random element from current set of elements. 
 Each element must have the same probability of being returned.
 Example:
 
 // Init an empty set.
 RandomizedSet randomSet = new RandomizedSet();
 
 // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 randomSet.insert(1);
 
 // Returns false as 2 does not exist in the set.
 randomSet.remove(2);
 
 // Inserts 2 to the set, returns true. Set now contains [1,2].
 randomSet.insert(2);
 
 // getRandom should return either 1 or 2 randomly.
 randomSet.getRandom();
 
 // Removes 1 from the set, returns true. Set now contains [2].
 randomSet.remove(1);
 
 // 2 was already in the set, so return false.
 randomSet.insert(2);
 
 // Since 2 is the only number in the set, getRandom always return 2.
 randomSet.getRandom();
 
 */

 class RandomizedSet {

  HashMap<Integer, Integer> valueSet = new HashMap<Integer, Integer>();
  Random random = new Random();
  ArrayList<Integer> value =new ArrayList<Integer>();
  
  /** Initialize your data structure here. */
  public RandomizedSet() {

  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
   if (valueSet.get(val) == null) {
    value.add(val);
    valueSet.put(val, value.size()-1);
    return true;
   }
   return false;

  }
  
  
 
  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
   boolean contain = valueSet.containsKey(val);
   if (!contain)
    return false;
   int dex = valueSet.get(val);
   if (dex < value.size() - 1) {
    int last = value.get(value.size() - 1);
    value.set(dex, last);
    valueSet.put(last, dex);
   }

   value.remove(value.size() - 1);
   valueSet.remove(val);
   return true;
  }

  /**
   * 逻辑不够清晰，而且还有逻辑的错误
   * */
  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove_error(int val) {
   if (valueSet.get(val) != null) {
    int dex = valueSet.get(val);
    value.set(dex, value.get(value.size()-1));
    value.remove(value.size()-1);
    if(value.size() > 0) {
     valueSet.put(value.get(value.size()-1),dex);
    }
    valueSet.remove(val);
    
    return true;
   }
   return false;
  }

  /** Get a random element from the set. */
  public int getRandom() {
   if(value.size()> 0) {
    int size = random.nextInt(value.size());
    return value.get(size);
   }else {
    return -1;
   }
  }
 }
 RandomizedSet instance = new RandomizedSet();
 public RandomizedSet get() {
  return instance;
 }

 public static void main(String[] args) {
  /**
   ["RandomizedSet","insert","insert","remove","insert","remove","getRandom"]
   [[],[0],[1],[0],[2],[1],[]] 
   * */ 
//  L380InsertDeleteGetRandomO1 test = new L380InsertDeleteGetRandomO1();
//  System.out.println(test.get().insert(0));
//  System.out.println(test.get().insert(1));
//  System.out.println(test.get().remove(0));
//  System.out.println(test.get().insert(2));
//  System.out.println(test.get().remove(1));
//  System.out.println(test.get().getRandom());
  
  /**
   ["RandomizedSet","remove","remove","insert","getRandom","remove","insert"]
   [[],[0],[0],[0],[],[0],[0]]
   * */
  L380InsertDeleteGetRandomO1 test1 = new L380InsertDeleteGetRandomO1();
  System.out.println(test1.get().remove(0));
  System.out.println(test1.get().remove(0));
  System.out.println(test1.get().insert(0));
  System.out.println(test1.get().getRandom());
  System.out.println(test1.get().remove(0));
  System.out.println(test1.get().insert(0));
 }

}
