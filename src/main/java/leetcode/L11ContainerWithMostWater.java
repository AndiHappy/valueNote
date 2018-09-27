package leetcode;

/**
 * @author zhailzh
 * 
 * @Date 2015121110:10:17
 * 
 */
public class L11ContainerWithMostWater {

	// Given n non-negative integers a1, a2, ..., an,
	// where each represents a point at coordinate (i, ai).
	// n vertical lines are drawn such that the two endpoints of line i is at
	// (i, ai) and (i, 0).
	// Find two lines, which together with x-axis forms a container, such that
	// the container contains
	// the most water.

	public static void main(String[] args) {
		int[] h = new int[]{166,63,82,92,144,186,192,68,101,179,50,170,141,31,149,131,180,94,
				168,142,91,137,159,184,89,135,101,21,70,191,136,37,7,18,81,103,4,26,171,106,5,173,28,
				99,4,129,30,185,23,198,127,67,88,39,51,129,126,104,150,196,48,38,185,7,56,19,110,12,
				45,33,70,50,7,98,149,163,179,131,100,3,82,180,70,170,171,73,51,97,130,153,45,130,191,31,137,199,2,199,11,47};
		int[] hg =new int[]{1,5,5,1};
		System.out.println(maxArea(hg));

	}

	/**
	 * 1. 理解题目，所找到的只是两个index的之间的面积最大，并不是总的面积最大
	 * 2. 这样的话，就需要想要index的移动的规律的总结，谁矮移动谁
	 * */
	public static int maxArea(int[] height) {
	    int maxA = 0;
	    int i = 0, j = height.length-1;
	    while(i < j) {
	        int H = Math.min(height[i], height[j]);
	        int W = j - i;
	        maxA = Math.max(maxA, H*W);
	        while(i < j && height[i] <= H) {
	            ++i;
	        }
	        while(i < j && height[j] <= H) {
	            --j;
	        }
	    }
	    return maxA;
	}	
	
	public static int maxArea2(int[] height) {
		return maxAreabyDP(height,height.length -1);
	}
	
	
	private static int maxAreabyDP(int[] h, int i) {
		int temp = i;
		if(i <= 1){
			return 0;
		}
		
		if(i<3 && i > 0){
			return h[i-1]> h[i]? h[i]:h[i-1];
		}
		
		if(h[i] <= h[i-1]){
			return maxAreabyDP(h, i-1)+h[i];
		}else{
			while(i> 0 && h[i] > h[i-1]){
				i--;
			}
			if(i == 0){
				return maxAreabyDP(h, temp-1)+h[temp-1];
			}else{
				return maxAreabyDP(h, i-1)+h[i]*(temp -i +1);
			}
		}
	}


	public int maxArea1(int[] height) {
		int[] flags = new int[height.length - 1];
		for (int i = 1; i < height.length; i++) {
			int first = height[i - 1];
			int seconde = height[i];

			if (first < seconde) {
				flags[i - 1] = 1;

			} else if (first > seconde) {
				flags[i - 1] = -1;

			} else {
				flags[i - 1] = 0;
			}
		}

		int value = cal(flags, height);
		return value;

	}

	private int cal(int[] flags, int[] h) {

		int value = 0;
		for (int i = 1; i < flags.length; i++) {
			if(flags[i] != flags[i-1]){
				value += calv(i,flags,h);
			}
		}
		
		return value;
	}

	private int calv(int i, int[] flags, int[] h) {
		int value = 0;
		if(flags[i] == 1){
			while(h[i] < h[i-1]){
				value += h[i-1]-h[i];
				i++;
			}
		}
		return value;
	}
}
