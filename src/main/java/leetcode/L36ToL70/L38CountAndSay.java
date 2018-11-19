package leetcode.L36ToL70;

/**
 * @author zhailz
 * 2018年11月16日 上午10:41:44
 */
public class L38CountAndSay {
	
	/**
	 * 
	 The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.


	 * */
	public static void main(String[] args){
		System.out.println("1");
		System.out.println("11");
		System.out.println(countAndSay(3)); System.out.print("##21");System.out.println();
		System.out.println(countAndSay(4));System.out.print("##1211");System.out.println();
		System.out.println(countAndSay(5));System.out.print("##111221");System.out.println();
		System.out.println(countAndSay(6));System.out.print("##312211");System.out.println();
		System.out.println(countAndSay(7));System.out.print("##13112221");System.out.println();
		System.out.println(countAndSay(8));System.out.print("##1113213211");System.out.println();
		System.out.println(countAndSay(9));System.out.print("##31131211131221");System.out.println();

	}

	public static String countAndSay(int n){
		if(n==1) return "1";
		if(n==2) return "11";
		String res = null;
		if(n > 2){
			String value = countAndSay(n-1);
			int i = 1,index = 1;
			StringBuilder builder = new StringBuilder();
			char tmp = value.charAt(0);
			while(index < value.length()){
				if(value.charAt(index) == tmp){
					i++;
				}else{
					builder.append(i).append(tmp);
					tmp = value.charAt(index);
					i=1;
				}
				index++;
			}
			builder.append(i).append(tmp);
			return builder.toString();
		}
		return res;
	}
	
	
	
}
