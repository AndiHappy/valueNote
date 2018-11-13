package leetcode.L0ToL35;

import java.util.Arrays;

/**
 * @author zhailzh
 * 
 * @Date 2016148:48:3
 * 

Given an array nums of n integers and an integer target, find three integers in 
nums such that the sum is closest to target. Return the sum of the three integers. 
You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

并且只有一个解

排序以后，移动相应的数据


 * 
 */
public class L16Lettcode3SumCloest {

    public int threeSumClosest(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        int small = num[0] + num[1] + num[2];
    	int big = num[num.length - 3] + num[num.length - 2] + num[num.length - 1];
    	if(small > target){
    		return small;
    	}
    	
    	if(big < target){
    		return target;
    	}
    	
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }
	
	public static void main(String[] args) {
		
/*		There are five very different kinds of skills and knowledge that are
 *  needed for success in software engineering:
 
			Technical mastery of a sufficient span of core CS disciplines 
			-- the technology space of software engineering, which includes languages, 
			tools, models and metrics, semantics and analysis, patterns and frameworks, 
			concurrency, process elements, and so on. 
			Having true depth in a representative set of current technologies 
			and practices (in each of these areas) matters more than the exact choices
			 of which particular areas.
			 
			An appreciation and respect for process and its elements, particularly small-team agile-like processes and the people who enact them. Again, the particular process models (agile, scrum, kanban, xp, etc.) matter less than an understanding of developer and team infrastructure, a willingness to be a team member, an understanding of the dynamics of human teams, and a capacity to respect the seemingly arbitrary rules of team participation. For larger projects, this is also about understanding the architectural enablers of the small-team model.
			An understanding of how to undertake creative design projects. This means appreciating the twin realities of engineering risk and technical debt, how they are assessed, and how they can be efficiently mitigated. In essence, this is about how to continually innovate and yet achieve successful outcomes.
			An ability to reach out into the world and stay current and informed regarding the ebb and flow of the socio-technical ecosystems that are the infrastructure of nearly all software projects. You rarely get to start with a clean sheet of paper, nor do you want to. Again, the choice of particular systems matters less that having an understanding of how to assess these systems from the standpoints of technical and market characteristics -- and how to engage, technically and socially, to move up the learning curve for these systems. This includes an ability to read code and review code. Like literature, ability to read and critique (and be critiqued) is an essential precursor of an ability to write.
			Finally, a willingness and ability to continually work to stay current with the technology. The pace of change is rapid, and skills and knowledge can go stale quickly without explicit action to stay current. By staying current and making this a part of your routine, you gain in engineering wisdom and become a more valuable player. This includes the skill of learning from other experts who are willing to review your work, including code, models and designs, tests and analyses, process plans, etc. Overcoming fear and committing to learning are always important, but especially deserving of explicit attention should you choose to rise through managerial ranks or be an entrepreneurial leader.

*/
}

}
