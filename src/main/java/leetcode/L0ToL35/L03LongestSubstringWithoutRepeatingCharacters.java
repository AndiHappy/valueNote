package leetcode.L0ToL35;

import java.util.HashMap;

/**
 * @author zhailzh
 * 
 * @Date 201511139:04:21
 * 
 *       Given a string, find the length of the longest substring without
 *       repeating characters. For example, the longest substring without
 *       repeating letters for "abcabcbb" is "abc", which the length is 3. For
 *       "bbbbb" the longest substring is "b", with the length of 1.
 * 
 *       子串必须连续，所以左边值在右边匹配到滑动窗口里面的数据的时候，随机更新
 */
public class L03LongestSubstringWithoutRepeatingCharacters {
	public static void main(String[] args) {
		L03LongestSubstringWithoutRepeatingCharacters chars = new L03LongestSubstringWithoutRepeatingCharacters();
		System.out.println(chars.lengthOfLongestSubstring3("abcabcbbpoiuytrewq"));

		System.out.println(chars.lengthOfLongestSubstring3("abcabcbbpo"));

		System.out.println(chars.lengthOfLongestSubstring3("abcabcbbpo"));

		System.out.println(chars.lengthOfLongestSubstring3("abcabcbbpo"));
	}


	public int lengthOfLongestSubstring3(String s) {
		char[] content = s.toCharArray();
		HashMap<Character, Integer> charindex = new HashMap<Character, Integer>();
		int temp = 0, // 当前匹配的长度
				max = 0, // 最大的长度
				start = 0;// 开始的位置，维持的left边界的位置
		for (int i = 0; i < content.length; i++) {
			char c = content[i];
			// 如果charindex里面没有对应的字符，或者有但是是以前匹配过的字符，因为charindex没有调用clear函数
			if (!charindex.containsKey(c) || charindex.get(c) < start) {
				temp++;
				charindex.put(c, i);
			} else {
				// 如果charindex里面有对应的字符，并且在start起始后面
				max = max > temp ? max : temp;// 计算匹配的长度
//------------------------------------------------
//				匹配过以后，起始位置需要重新的计算，temp的长度也需要重新的计算
				start = charindex.get(c) + 1;// 计算开始的位置
				temp = i - start + 1;
//------------------------------------------------
				charindex.put(c, i);
			}
		}
		return max > temp ? max : temp;
	}
}
