package leetcode.L;

import java.util.Arrays;

public class L283MoveZeros {

    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length <= 1) return;
        int j = 0;
        for (int i = 0;i < nums.length;i++){
            if(nums[i] != 0){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                j++;
            }
        }
    }

    public static void main(String[] args){
        L283MoveZeros solution = new L283MoveZeros();
        int[] nums = new int[]{0,1,0,3,12};
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
