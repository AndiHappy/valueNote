package leetcode.L71ToL100;

/**
 * @author guizhai
 *
 */
public class L88MergeSortedArray {

	/**

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional
 elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

	 */
	
	 public void merge(int[] first, int m, int[] second, int n) {
		 
     int location = m + n - 1;
     
     int left = m - 1, right = n - 1;
     
     while (left >= 0 || right >= 0) {
    	 
         if (left >= 0 && right >= 0) {
        	 int value ;
        	 if(first[left] >= second[right]) {
        		 value= first[left];
        		 left--;
        	 }else {
        		 value = second[right];
        		 right--;
        	 }
        	 first[location] = value;
        	 location--;             
         } else {
        	 if(left >= 0) {
        		 first[location] = first[left];
        		 left--;
          	 location--;   
        	 }else {
        		 first[location] = second[right];
        		 right--;
          	 location--;   
        	 }
         }
     }

 }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
