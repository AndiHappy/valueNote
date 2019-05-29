package leetcode.L130ToL140;

/**
 * @author guizhai
 *
 */
public class L134GasStation {

	/**

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel 
from station i to its next station (i+1). You begin the journey with an empty 
tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.
Example 1:

Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:

Input: 
gas  = [2,3,4]
cost = [3,4,3]

Output: -1

Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.

	 */
	// If there exists a solution, it is guaranteed to be unique.
	// 这里有一个比较特殊的设定，如果有那一定只有一个：
	
	/*
	If you are confused,(like I was before),try think about it with two passes.
	
	 Use the first pass to determine if we have a solution(property 2 above). 
	 
	 Then use the second pass to find out the start positon(use property 1). 
	 
	 After you are comfortable with 2 passes,
	 
	 you can absolutely modify it into one pass solution.

*/
	//Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
  //	otherwise return -1.

	public int canCompleteCircuit(int[] gas, int[] cost) {
    /**

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
otherwise return -1.

determine if we have a solution

只有循环一周才能够返回出发点。

     * */
		
    int total = 0;
    for (int i = 0; i < gas.length; i++) {
        total += gas[i] - cost[i];
    }
    if (total < 0) {
        return -1;
    }

    // find out where to start
    // 如果有，并且只有一条，那么从其他的地方出发的时候，总会遇到问题的
    int tank = 0;
    int start = 0;
    for (int i = 0; i < gas.length;i++) {
        tank += gas[i] - cost[i];
        // If car starts at A and can not reach B. 
        // Any station between A and B can not reach B.
        // (B is the first station that A can not reach.), 
        // so the next possible start station is B.
        if (tank < 0) {
            start = i + 1;
            tank = 0;
        }
    }
    return start;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
