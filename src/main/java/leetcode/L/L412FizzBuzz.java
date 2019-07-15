package leetcode.L;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L412FizzBuzz {

	/**
	Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of 
five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
	 */
	
	public List<String> fizzBuzz(int n) {
    if(n <=0) return null;
    List<String> res = new ArrayList<String>();
    for (int i = 1; i <= n; i++) {
    	if(i%5==0 && i%3 == 0) {
    		res.add("FizzBuzz");
    		continue;
    	}else if(i%3 == 0) {
    		res.add("Fizz");
    		continue;
    	}else if(i%5 == 0) {
    		res.add("Buzz");
    		continue;
    	}else {
    		res.add(i+"");
    	}
		}
    return res;
  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
