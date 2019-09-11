package leetcode.L0ToL35;

/**
 * @author zhailz
 *
 * @version 2018年9月28日 下午10:08:33

Given two integers dividend (被除数) and divisor (除数), divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only 
store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, 
assume that your function returns 231 − 1 when the division result overflows.

 */
public class L29DivideTwoIntegers {

	/**
	 * 题目本身的难度不大，但是边界条件的处理需要格外的消息，特别是正负数的边界:
	 * 2147483647
	 * -2147483648
	 * 以及这两个边界值的处理，以及绝对值的计算
	 * */
	
	
	public int divide(int dividend, int divisor) {
		//首先判定符号的问题
		boolean flag = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);

		int dividends = dividend > 0 ? -dividend : dividend;
		int divisors = divisor > 0 ? -divisor : divisor;
		
		int res = 0;int curdivisors = divisors;
		while (dividends <= divisors) {
			int tmp = 1;
			while( (divisors << 1 >= dividends) && (divisors << 1) < 0 ) {
				tmp <<=1;
				divisors <<=1;
			}
			
			res+=tmp;
			dividends -= divisors;
			divisors = curdivisors;
		}
		return flag ? res==Integer.MIN_VALUE?Integer.MAX_VALUE:res : -res;
	}
	
	
	public int divide_copy(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }

    if (dividend > 0 && divisor > 0) {
        return divideHelper(-dividend, -divisor);
    } else if (dividend > 0) {
        return -divideHelper(-dividend, divisor);
    }
    else if(divisor > 0) {
        return -divideHelper(dividend, -divisor);
    }
    else {
        return divideHelper(dividend, divisor);
    }
}

private int divideHelper(int dividend, int divisor){
    int res = 0;
    int currentDivisor = divisor;
    while(dividend<=divisor){    // abs(divisor) <= abs(dividend)
    	
        int temp = 1;  
        //test max temp for: temp * abs(divisor) <= abs(dividend), while temp = 2^n
        while( (currentDivisor << 1) >=dividend && (currentDivisor << 1) <0 ){  
            temp <<=1;
            currentDivisor <<=1;
        }
        
        dividend -= currentDivisor;  
        res += temp;    
        currentDivisor = divisor;
    }       
    return res;
}

	//这种情况会直接的超时
	public int divideStep2(int dividend, int divisor) {
		//首先判定符号的问题
		boolean flag = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
		long dividends = Math.abs((long)dividend);
		long divisors = Math.abs((long)divisor);
		long res = 0,step = divisors,time= 1;
		while (dividends - divisors >= 0) {
			if(dividends >= step){
				res = res+time;
				dividends -= step;
				step = step<<1;
				time = time<<1;
			}else{
				step = step>>1;
				time = time>>1;
			}
		}
		//这样写会有溢出的问题，所以需要判断
		return (int) (flag ? res>Integer.MAX_VALUE?Integer.MAX_VALUE:res : -res<Integer.MIN_VALUE?Integer.MIN_VALUE:-res);
	}

	/**
	 * 调整步长
	 * */
	public int divideStep(int dividend, int divisor) {
		if (dividend == 0)
			return 0;//除数为0、1、-1直接返回
		if (divisor == 1)
			return dividend;
		if (divisor == -1)
			return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;

		boolean isNeg = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
		long ldividend = Math.abs((long) dividend);//先强制转型为long
		long ldivisor = Math.abs((long) divisor);

		long res = 0, curr = 1;
		long sub = ldivisor;//动态变化除数,改变步长避免超时
		while (ldividend >= ldivisor) {
			if (ldividend >= sub) {
				res += curr;
				ldividend -= sub;
				sub = sub << 1;
				curr = curr << 1;
			} else {
				sub = sub >> 1;
				curr = curr >> 1;
			}
		}

		return isNeg ? (int) -res : (int) res;//已经考虑if(divisor==-1)会溢出的情况
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Integer.MIN_VALUE);
//		
//		System.out.println(Math.abs(Integer.MAX_VALUE));
//		System.out.println(Math.abs(Integer.MIN_VALUE));
//		
//		long value = Long.MAX_VALUE;
//		System.out.println((int)value);
//		value = Long.MIN_VALUE;
//		System.out.println((int)value);
		
		
		L29DivideTwoIntegers test = new L29DivideTwoIntegers();
				System.out.println(test.divide(10, 3));
				System.out.println(test.divide(-10, 3));
				System.out.println(test.divide(-100, -3));
				
		System.out.println(test.divide(-2147483648, 1)); //-2147483648
		System.out.println(test.divide(-2147483648, -1)); //-2147483648

	}

}
