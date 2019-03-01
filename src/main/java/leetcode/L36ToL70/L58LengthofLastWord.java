package leetcode.L36ToL70;

public class L58LengthofLastWord {

	/***
	 
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5
	  
	 * */
	
public int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0) return 0;
        String[] tmp = s.split(" ");
        return tmp.length > 0 ?tmp[tmp.length-1].length():0;
    }


	public static void main(String[] args) {
		L58LengthofLastWord test = new L58LengthofLastWord();
		System.out.println(test.lengthOfLastWord("a"));

	}

}
