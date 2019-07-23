package leetcode.L0ToL35;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhailz
 *
 * @version 2018年9月29日 下午4:00:20
 */
public class L30SubstringwithConcatenationofAllWords {

	/*
	
	
	You are given a string, s, and a list of words, words, that are all of the same length.
	Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
	
	Example 1:
	
	Input:
	  s = "barfoothefoobarman",
	  words = ["foo","bar"]
	Output: [0,9]
	Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
	The output order does not matter, returning [9,0] is fine too.
	Example 2:
	
	Input:
	  s = "wordgoodstudentgoodword",
	  words = ["word","student"]
	Output: []
	*/
	
	/**
	 * 首先是通过循环的是word的的长度，然后维持一个窗口，如果这个窗口能够匹配的话，增大这个窗口，如果不行则降低这个窗口
	 * 首先是最外层的循环，只需要能够容纳下一个word[0]的匹配
	 * 
	 * 然后就是窗口的维护，开始标识k,结束标识j,开始于循环的开始变量i，首先就是限定条件的匹配，根据题目中设定：
	 * 
	 *  words, that are all of the same length.
	 *  
	 * 所以只需要比对的是 word[0] 长度的字符，然后匹配了就扩大窗口，不匹配则转译匹配的开始节点，这里的转译针对的匹配的字符串
	 * 可见这里又是一个循环
	 * 
	 * */
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<Integer>();
		if (words.length == 0 || words[0].length() == 0)
			return res;

		//字典
		Map<String, Integer> wordDict = new HashMap<String, Integer>();

		//保存字符串，是否有重复的
		for (String word : words) {
			if (!wordDict.containsKey(word))
				wordDict.put(word, 1);
			else
				wordDict.put(word, wordDict.get(word) + 1);
		}

		//当前匹配的字符串
		Map<String, Integer> currWords = new HashMap<String, Integer>();
		//同样长度的原因
		int len = words[0].length();
		//最外层的循环,其实这里的len的确定，可以选定words[0].length()，也能够选择words[words.length -1].length();
		for (int i = 0; i < len; i++) {
			int k = i, j = i; //k is at the head of the window and j is the last.
			int addedCount = 0; //to indicate whether we add index to res.
			while (k <= s.length() - len * words.length && j + len <= s.length()) { //make sure the remaining length is enough.
				//
				String subWord = s.substring(j, j + len);
				//如果不能包含某一个子串的值，直接的跳过
				if (!wordDict.containsKey(subWord)) { //the substring is not in words, head jumps to the right of this substring.
					addedCount = 0;
					currWords.clear();
					j += len;
					k = j;
					continue;
				}

				//这个时候，wordDict中已经包含了这个subword，这个时候，需要判定current中是否已经包含了
				if (!currWords.containsKey(subWord) || currWords.get(subWord) != wordDict.get(subWord)) {
					if (!currWords.containsKey(subWord))
						currWords.put(subWord, 1);
					else
						currWords.put(subWord, currWords.get(subWord) + 1); //update the current words we used.
					addedCount++;
					//匹配完成，记录结果
					if (addedCount == words.length) { //if get a index, add it to res. And we need to continue checking
						res.add(k);
						addedCount--; //remove the head and check new substring, so count-- and move head to new position.
						String preHead = s.substring(k, k + len);
						if (currWords.get(preHead) == 1)
							currWords.remove(preHead); //update the currWords baseAlg.map.
						else
							currWords.put(preHead, currWords.get(preHead) - 1);
						k += len;
					}
					j += len;
				} else { //the current substring was used out before. Move head len steps right.
					String preHead = s.substring(k, k + len);
					addedCount--;
					if (currWords.get(preHead) == 1)
						currWords.remove(preHead); //update the currWords baseAlg.map.
					else
						currWords.put(preHead, currWords.get(preHead) - 1);
					k += len; //don't move j this case.
				}
			}
			currWords.clear();
		}
		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		L30SubstringwithConcatenationofAllWords test = new L30SubstringwithConcatenationofAllWords();
		String s = "barfoothefoobarmanbarfobarfoo";
		String[] words = new String[] { "foo"};
		List<Integer> v = test.findSubstring(s, words);
		System.out.println(v.toString());
		s = "wordstudgoodstudentgoodwordstud";
		words = new String[] { "word", "student" };
		v = test.findSubstring(s, words);
		System.out.println(v.toString());
	}

}
