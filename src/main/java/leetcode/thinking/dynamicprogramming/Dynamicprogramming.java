package leetcode.thinking.dynamicprogramming;

/**
 * @author guizhai
 *
 */
public class Dynamicprogramming {

	/**
	 * when to use:
	
1.) Optimal substructure: In layman terms, if I could break a problem into smaller versions of it, 
and then combine solutions to the smaller problems, I'll have solved the problem at hand. 

2.) Overlapping subproblems:  While solving those smaller problem that leads to the same repetitive 
calculation steps, we term these as overlapping sub problems. 

Hence instead of solving for the same calculation steps over and over, 
we can store solutions to previous calculated steps it into a memory such as hash baseAlg.map or an array. 

Note: Some problems that exhibit optimal substructure but not overlapping subproblems are termed  
'divide and conquer' method instead. Such as merge sort.

Edit: I have kindof internalised the above as simply figuring out whether a solution to a problem 
can be built from a bottom to top approach. 

So instead of the standard Fibionacci algorithm that does fib(n − 1) + fib(n − 2), 
you would do fib(1), fib(2) ... fib(n) and  'cache' intermittent result.

Either that, or we could also view problem as: if we can cache it to optimize, cache it ! 
(at the expense of memory of course)

	 */
	public static void main(String[] args) {
		//最优子结构和重叠子问题

		/**
		 
		 In computer science, a problem is said to have optimal substructure 
		 if an optimal solution can be constructed from optimal solutions of its subproblems. 
		 This property is used to determine the usefulness of dynamic programming and greedy algorithms for a problem.[1]
		 
		 最优子结构，对应的是贪心算法和动态规划

         Typically, a greedy algorithm is used to solve a problem with 
         optimal substructure if it can be proven by induction that this is optimal at each step.[1] 
         
         Otherwise, provided the problem exhibits overlapping subproblems as well, dynamic programming is used. 
         
         If there are no appropriate greedy algorithms and the problem fails to exhibit overlapping subproblems,
         often a lengthy but straightforward search of the solution space is the best alternative.
         
         贪心算法适合的是每一步都会最优的。
         重复子问题的特性，适合动态规划
         
		 * */
	}

}
