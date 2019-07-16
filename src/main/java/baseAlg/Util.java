package baseAlg;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.RandomAccess;

public class Util {
	
	 public static Random rnd;
	 
	 /**
	  * 打乱数组的顺序
	  * */
	 public static void shuffle(List<?> list) {
		 if(rnd == null) rnd = new Random();
		 shuffle(list, rnd);
	 }
	
	 private static void swap(Object[] arr, int i, int j) {
	    Object tmp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = tmp;
	}
		
	 /**
	  * 打乱数组的顺序
	  * */
	  @SuppressWarnings({"rawtypes", "unchecked"})
	  public static void swap(List<?> list, int i, int j) {
	      // instead of using a raw type here, it's possible to capture
	      // the wildcard but it will require a call to a supplementary
	      // private method
	      final List l = list;
	      l.set(i, l.set(j, l.get(i)));
	  }
	  
		 @SuppressWarnings({"rawtypes" , "unchecked"})
		public static void shuffle(List<?> list, Random rnd) {
	     int size = list.size();
	     if (size < 5 || list instanceof RandomAccess) {
	    	 // i= size,size-1,..2
	         for (int i=size; i>1; i--) {
	        	 int index = rnd.nextInt(i);
	        	 swap(list, i-1, index);
	         }
	     } else {
	         Object arr[] = list.toArray();
	         // Shuffle array
	         for (int i=size; i>1; i--)
	             swap(arr, i-1, rnd.nextInt(i));
	         // Dump array back into list
	         // instead of using a raw type here, it's possible to capture
	         // the wildcard but it will require a call to a supplementary
	         // private method
					ListIterator it = list.listIterator();
	         for (int i=0; i<arr.length; i++) {
	             it.next();
	             it.set(arr[i]);
	         }
	     }
	 }

	
	/**
	 * 查找升序的数组中中，包含某一个值
	 * @return 如果包含则返回对应的索引值，如果 不包含则返回-1
	 * */
	public static int  sortArraySearchKey(int[] is, int target) {
		if(is == null || is.length == 0) return -1;
		 int from = 0; int to = is.length-1;
		 while(from <= to) {
			 int mid = (from + to)/2;
			 if(is[mid] == target) return mid;
			 if(is[mid] > target) to = mid-1;
			 if(is[mid] < target) from = mid+1;
		 }
		return -1;
	}
	
	/**
	 * 查找升序的数组中中，是否包含某一个值
	 * @return 是否包含
	 * */
	public static boolean  sortArrayHasKey(int[] is, int target) {
		return sortArraySearchKey(is, target) >= 0;
	}
	
	/**
	 * 交换数组下标的对应的值
	 * */
	public static void swap(int[] nums, int i, int j) {
		nums[i] = nums[i] + nums[j] - (nums[j] = nums[i]);
	}
	
	public static void main(String[] args) {
		int[] value = new int[] {1,9};
		System.out.println(Arrays.toString(value));

		swap(value, 0, 1);
		System.out.println(Arrays.toString(value));
	}

}
