package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L60PermutationSequence {

	/**
	 * The set [1,2,3,...,n] contains a total of n! unique permutations.
	 * 
	 * By listing and labeling all of the permutations in order, we get the
	 * following sequence for n = 3:
	 * 
	 * "123" "132" "213" "231" "312" "321" Given n and k, return the kth
	 * permutation sequence.
	 * 
	 * Note:
	 * 
	 * Given n will be between 1 and 9 inclusive. Given k will be between 1 and
	 * n! inclusive. Example 1:
	 * 
	 * Input: n = 3, k = 3 Output: "213" Example 2:
	 * 
	 * Input: n = 4, k = 9 Output: "2314"
	 * 
	 */

	// 确定每一位的数字
	// 排序的过程比较的重要
	public String getPermutation(int n, int k) {
        List<Integer> nums=new ArrayList<>();
        String ans=new String();
        
        int mod=1;
        for(int i=1;i<=n;i++){
            nums.add(i);
            mod=mod*i;
        }
        k--;
        
        for(int i=0;i<n;i++){
            mod=mod/(n-i);
            int cur=k/mod;
            k%=mod;
            ans+=nums.get(cur);
            nums.remove(cur);
        }
        
        return ans;
    }

	public static void main(String[] args) {
		L60PermutationSequence test = new L60PermutationSequence();
		test.getPermutation(4, 10);

	}

}
