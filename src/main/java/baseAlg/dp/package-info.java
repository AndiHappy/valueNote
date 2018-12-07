/**
 * @author zhailz
 * 2018年11月29日 下午8:35:07
 * 
I’ll conclude this post with some hints that I use when trying to decide whether 
or not to use dynamic programming for a given problem:

Can the problem be divided into subproblems of the same kind?
Can I define the previous division by a recurrence definition? That is, define F(n) as a function of F(n-1)
Will I need the results to the sub-problems multiple times or just once?
Does it make more sense to use a top-down or a bottom-up approach?
Do I need to worry about the stack if I use a memoized recursive approach?
Do I need to keep all previous results or can I optimize the space and keep just some of them?

 */
package baseAlg.dp;