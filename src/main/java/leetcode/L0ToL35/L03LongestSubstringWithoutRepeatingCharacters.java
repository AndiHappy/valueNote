package leetcode.L0ToL35;
/*
 * NOTE: 
 * 字符的算法有一个特定的隐藏的条件，总数为256个
 * 维护窗口的一个算法，起始点，终止点，窗口大小，其中关键的在于起始点的更新（后退，前进）
 */
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
		System.out.println(chars.lengthOfLongestSubstring3("abcabcbbpoiuytrewq")+ " 11");
		System.out.println(chars.lengthOfLongestSubstring("abcabcbbpoiuytrewq")+ " 11");
		
		System.out.println(chars.lengthOfLongestSubstring("dvdf") + " 3");
		System.out.println(chars.lengthOfLongestSubstring("abba") + " 2");
		System.out.println(chars.lengthOfLongestSubstring3("abcabcbbpo")+ " 3");
		System.out.println(chars.lengthOfLongestSubstring("abcabcbbpo")+ " 3");

	}

	public int lengthOfLongestSubstring(String s) {
		int[] tmp = new int[256];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = -1;
		}
		char[] calue = s.toCharArray();
		int maxLen = 0;int start = 0;
		for (int i = 0; i < calue.length; i++) {
			//说明已经存在这个元素了
			if(tmp[calue[i]] != -1  && tmp[calue[i]] >= start) {
				start = tmp[calue[i]]+1;
			}
			tmp[calue[i]] = i;
			if(maxLen <i-start+1) {
				maxLen = i-start+1;
			}
			
		}
		return maxLen;
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
