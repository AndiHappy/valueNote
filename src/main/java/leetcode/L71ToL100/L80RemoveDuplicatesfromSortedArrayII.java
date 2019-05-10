package leetcode.L71ToL100;

/**
 * @author guizhai
 *
 */
public class L80RemoveDuplicatesfromSortedArrayII {

	/**
	 * @param args
	 */
	
	/**
	 * remove duplicates from sorted array 一题的扩展版本，要求时可以出现连续的重复，但是重复次数不能超过 2 
	 * */
	public static int removeDuplicates(int[] nums) {
    if (nums.length < 3) return nums.length;
    int p = nums[1], pp = nums[0], pos = 2;
    for (int i = 2; i < nums.length; i++) {
        int num = nums[i];
        if (num != p || num != pp) {
            nums[pos++] = num;
            pp = p;
            p = num;
        }
    }
    return pos;
}
	
	/**
	 * 优化的依据：有序的数据
	 * */
	public static int removeDuplicates_good(int[] nums) {
    if (nums.length < 3) return nums.length;
    int pos = 2;
    for (int i = 2; i < nums.length; i++) {
        if (nums[i] != nums[pos-2]) {
            nums[pos++] = nums[i];
        }
    }
    return pos;
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
