package leetcode.L71ToL100;

import java.util.Stack;

/**
 * @author guizhai
 *
 */
public class L84LargestRectangleinHistogram {

	/**

Given n non-negative integers representing the histogram's bar height 
where the width of each bar is 1, find the area of largest rectangle in the histogram.

 


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

 


The largest rectangle is shown in the shaded area, which has area = 10 unit.

 

Example:

Input: [2,1,5,6,2,3]
Output: 10


	 */
	
	public int largestRectangleArea(int[] heights) {
    /*
    height: 1,3,4,2,5
    index: 0,1,2,3,4
    stack to save index
    it pop when it find current h[i]<stack.peek()
    right boundary = i-1
    left boundary = stack.peek()+1
    exp: stack 0,1,2. i=3, 2 is poped. 
    right boundary is 3-1 = 2
    left boundary is 1+1 =2
    width = right-left +1 = 1
    then the rect for now is 4(current height)*1 = 4
    h[i] is still < stack.peek() = 3
    index 1 and height 3 is poped.
    right boundary remain same i-1 = 2
    left boundary is 0+1 = 1
    width = 2-1+1 = 2
    rect = width *current hight = 2*3 = 6(6>4 so update the area)
    */
		
    int len = heights.length;
    Stack<Integer> idxs = new Stack<>();
    int i=0;
    int rect = 0;
    while(i<=len){
        int hi = i==len?0:heights[i];
        if(idxs.isEmpty() || hi>heights[idxs.peek()]){
            idxs.push(i);
            i++;
        }else{
            int curHeight = heights[idxs.pop()]; 
            int rightBound = i-1;
            int leftBound = idxs.isEmpty()? 0:idxs.peek()+1;
            int width = rightBound-leftBound+1;
            rect = Math.max(rect, curHeight*width);
        }
    }
    return rect;
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
