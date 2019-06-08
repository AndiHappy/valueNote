package leetcode.L130ToL140;

/**
 * @author guizhai
 *
 */
public class L137SingleNumberII {

	/**
	
	Given a non-empty array of integers, every element appears three times except for one, 
	which appears exactly once. Find that single one.
	
	Note:
	
	Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	
	Example 1:
	
	Input: [2,2,3,2]
	Output: 3
	Example 2:
	
	Input: [0,1,0,1,0,1,99]
	Output: 99
	
	 */

	/**
	 
	 
数组为[2,2,2,3]，一共有四个元素，进行四次循环。

第一次循环，b=(0000^0010)&1111=0010=2，a=(0000^0010)&1101=0000=0

第二次循环，b=(0010^0010)&1111=0000=0，a=(0000^0010)&1111=0010=2

第三次循环，b=(0000^0010)&1101=0000=0，a=(0010^0010)&1111=0000=0

第四次循环，b=(0000^0011)&1111=0011=3，a=(0000^0011)&1100=0000=0

不知道大家有没有发现，某个值nums[i]第一次出现的时候，b把它记录了下来，这时候a=0；接着第二次出现的时候，b被清空了，记录到了a里面；接着第三次出现的时候，b和a都被清空了。

如果一个数组中，所有的元素除了一个特殊的只出现一次，其他都出现了三次，那么根据我们刚刚观察到的结论，最后这个特殊元素必定会被记录在b中。

有些朋友会说，但是不一定数组都是刚好3个2都在一起，3个4都在一起，都能够满足刚刚这样子的做法。

上上篇博客136题中，笔者本人提出了异或其实是满足交换律和结合律的，而且&这个操作也是满足交换律和结合律的，所以无论3个2会不会一起出现，结果都是会刚好抵消的。

所以上述的方法可以解决这个问题。

怎么想出这种方法的：
其实discuss区的大神是设计了一种方法，借由这种方法推出了a和b的变换方式…

我们想要达到的效果其实是——

　　　　　　　　　　  a  b

初始状态　　　　  ：   0   0

第一次碰见某个数x：   0   x（把x记录在b中）

第二次碰见某个数x：   x   0（把x记录在a中）

第三次碰见某个数x：   0   0（把a和b都清空，可以处理其他数）

还记得我们之前处理“所有元素都出现两次，只有一个特殊元素出现一次”的问题吗？其实我们那会想要达到的状态也是——

　　　　　　　　　　  a  

初始状态　　　　  ：   0

第一次碰见某个数x：   x(把x记录在a中）

第二次碰见某个数x：   0(把a清空）

然后我们刚好就找到了异或运算可以处理这个问题。

那么这次我们同样利用异或运算，看能不能设计出一种变换的方法让a和b按照上述变换规则，进行转换。

b=0时碰到x，就变成x；b=x时再碰到x，就变成0，这个不就是异或吗？所以我们也许可以设计b=b xor x。

但是当b=0时再再碰到x，这时候b还是要为0，但这时候不同的是a=x，而前两种情况都是a=0。所以我们可以设计成：b=(b xor x)&~a

同样道理，我们可以设计出：a=(a xor x)&~b

在这种变换规则下，a和b都能按照我们设定的状态来发生转化。最后那个只出现一次的元素必定存储在b中。

 
感想：
异或真的是个神奇的东西，它其实已经内含了“判断”的过程。想一下我们“所有元素都出现两次，只有一个特殊元素出现一次”的问题，我们如果设计一个int型整数result用来记录，假定数组为[2,3,4,2,3]，我们当然可以不断地result+2+3+4，但是到了第二次出现2的时候，要怎么判断这个2已经出现过了，这时候要result-2呢？但是异或就可以，只要你出现过一次，它就会永久记得你。

话说异或是怎么实现的？我记得好像跟二进制加法有关？
	 
	 * */
	public int singleNumber(int[] nums) {
		int a = 0, b = 0;
		for (int i = 0; i < nums.length; ++i) {
			b = (b ^ nums[i]) & ~a;
			a = (a ^ nums[i]) & ~b;
		}
		return b;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}