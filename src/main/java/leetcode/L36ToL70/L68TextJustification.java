/**
 * 
 */
package leetcode.L36ToL70;

import java.util.ArrayList;

/**
 * @author guizhai
 *
 */
public class L68TextJustification {

	/**
	 
	Given an array of words and a width maxWidth, format the text 
	
	such that each line has exactly maxWidth characters and is fully (left and right) justified.
	
	You should pack your words in a greedy approach; 
	that is, pack as many words as you can in each line. 
	
	Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
	
	Extra spaces between words should be distributed as evenly as possible. 
	If the number of spaces on a line do not divide evenly between words, 
	the empty slots on the left will be assigned more spaces than the slots on the right.
	
	For the last line of text, it should be left justified 
	and no extra space is inserted between words.
	
	Note:
	
	A word is defined as a character sequence consisting of non-space characters only.
	Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
	The input array words contains at least one word.
	
	Example 1:
	
	Input:
	words = ["This", "is", "an", "example", "of", "text", "justification."]
	maxWidth = 16
	Output:
	[
	 "This    is    an",
	 "example  of text",
	 "justification.  "
	]
	Example 2:
	
	Input:
	words = ["What","must","be","acknowledgment","shall","be"]
	maxWidth = 16
	Output:
	[
	"What   must   be",
	"acknowledgment  ",
	"shall be        "
	]
	
	Explanation: Note that the last line is "shall be    " instead of "shall     be",
	           because the last line must be left-justified instead of fully-justified.
	           Note that the second line is also left-justified becase it contains only one word.
	Example 3:
	
	Input:
	words = ["Science","is","what","we","understand","well","enough","to","explain",
	       "to","a","computer.","Art","is","everything","else","we","do"]
	maxWidth = 20
	Output:
	[
	"Science  is  what we",
	"understand      well",
	"enough to explain to",
	"a  computer.  Art is",
	"everything  else  we",
	"do                  "
	]
	
	 */

	public ArrayList<String> fullJustify(String[] words, int L) {
		ArrayList<String> res = new ArrayList<String>();
		if (words == null || words.length == 0)
			return res;
		int count = 0;
		int last = 0;
		for (int i = 0; i < words.length; i++) {
			//count 积累的临时的长度
			// words[i] 准备放进去的字符的长度
			// 字符之间的空格数量
			if (count + words[i].length() + (i - last) > L) {
				int spaceNum = 0;
				int extraNum = 0;
				
				//计算当前的间隔数：i - last - 1  因为当前的words[i]加入失败了。
				if (i - last - 1 > 0) {
					
					// spaceNum 空格的数量
					spaceNum = (L - count) / (i - last - 1);
					
					// 剩余的数量，没有被分配干净的。
					extraNum = (L - count) % (i - last - 1);
				}
				
				StringBuilder str = new StringBuilder();
				for (int j = last; j < i; j++) {
					str.append(words[j]);
					if (j < i - 1) { //i标识的字符并没有添加成功
						for (int k = 0; k < spaceNum; k++) {
							str.append(" ");
						}
						if (extraNum > 0) {
							str.append(" ");
							extraNum--;
						}
					
					}
				}
				
				//一个字符占一行的情况
				for (int j = str.length(); j < L; j++) {
					str.append(" ");
				}
				res.add(str.toString());
				count = 0;
				last = i;
			}
			
			count += words[i].length();
			
		}
		
		//最后一行
		StringBuilder str = new StringBuilder();
		for (int i = last; i < words.length; i++) {
			str.append(words[i]);
			if (str.length() < L)
				str.append(" ");
		}
		for (int i = str.length(); i < L; i++) {
			str.append(" ");
		}
		res.add(str.toString());
		return res;
	}

	public static void main(String[] args) {
		L68TextJustification test = new L68TextJustification();
		String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
		ArrayList<String> res = test.fullJustify(words , 16);
		System.out.println(res);

	}

}
