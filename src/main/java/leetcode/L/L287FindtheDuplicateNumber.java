/**
 * 
 */
package leetcode.L;

/**

Given an array nums containing n + 1 integers where 

each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.

Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).

You must use only constant, O(1) extra space.

Your runtime complexity should be less than O(n2).

There is only one duplicate number in the array, but it could be repeated more than once.
 *
 */
public class L287FindtheDuplicateNumber {
	
/**
 * 如果是node节点那是，1-》2-》3-》4(圈的开始节点)-》5-》6-》7-》4(圈的结束节点)
 * slow 和fast相遇的只能是圈中的某一个点，在这个实例找那个 1，2，3，4，5，6，7，4
 * 
value:1 2 3 4 5
slowi:0 1 2 3 4

value:1 3 5 7 5
fasti:0 2 4 6 4

如果slow经过的节点的个数为l，那么2l，他们在圈中的某处首次相遇了。那么l就是那个那个圈的长度
这样的话，我们假设从头到圈的开始点为a,圈开始点到相遇点为b，相遇点再到开始点为c，那么有
l = b+c
a+b = l
a = l-b
c = l-b
也就是说a=c

所以才有了从头开始，直到再次的相遇的，就是圈的开始的点
 * */
	public static int findDuplicate(int[] nums) {
	        if(nums == null || nums.length <= 1) return 0;
	        int slow = 0;
	        int fast = 0;
//	        StringBuilder slowBuilder = new StringBuilder();
//	        StringBuilder falseBuilder = new StringBuilder();

	        do{
//	        	slowBuilder.append(slow).append(" ");
//	        	falseBuilder.append(fast).append(" ");
	            slow = nums[slow];
	            fast = nums[nums[fast]];
	        }while(slow != fast);
//	        slowBuilder.append(slow).append(" ");
//	        falseBuilder.append(fast).append(" ");
//	        System.out.println(slowBuilder.toString());
//	        System.out.println(falseBuilder.toString());
	        // 此时fast 和 slow的值，唯一nums[slow] = nums[fast];
	        slow = 0;
	        while(slow != fast){
	            slow = nums[slow];
	            fast = nums[fast];
	        }
	        return slow;
	 }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] tmp = new int[] {1,2,3,4,5,6,7,4};
		L287FindtheDuplicateNumber test = new L287FindtheDuplicateNumber();
		int value = test.findDuplicate(tmp);
		System.out.println(value);

	}

}
