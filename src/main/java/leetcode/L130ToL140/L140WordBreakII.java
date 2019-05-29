package leetcode.L130ToL140;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author guizhai
 *
 */
public class L140WordBreakII {

	/**
	
	
	Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
	
	add spaces in s to construct a sentence where each word is a valid dictionary word. 
	Return all such possible sentences.
	
	Note:
	
	The same word in the dictionary may be reused multiple times in the segmentation.
	You may assume the dictionary does not contain duplicate words.
	// 字典里面没有重复的字符串
	Example 1:
	
	Input:
	s = "catsanddog"
	wordDict = ["cat", "cats", "and", "sand", "dog"]
	Output:
	[
	"cats and dog",
	"cat sand dog"
	]
	Example 2:
	
	Input:
	s = "pineapplepenapple"
	wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
	Output:
	[
	"pine apple pen apple",
	"pineapple pen apple",
	"pine applepen apple"
	]
	Explanation: Note that you are allowed to reuse a dictionary word.
	Example 3:
	
	Input:
	s = "catsandog"
	wordDict = ["cats", "dog", "sand", "and", "cat"]
	Output:
	[]
	
	 */

	/**
	Scan the the string from the tail
	Build possible solution for the current index based on DP results
	Return the solution when index==0
	 * */
	public List<String> wordBreak_DP(String s, Set<String> dict) {
		/**
		 * 这个数据结构，比较的关键，存储的是i 之后的匹配的数据，如果匹配了之后，就会放到这个map中
		 * 最主要的是，采取了从tail遍历开始的方法，符合了从低向上的标准
		 * */
		Map<Integer, List<String>> validMap = new HashMap<Integer, List<String>>();

		// initialize the valid values
		List<String> l = new ArrayList<String>();
		l.add("");
		validMap.put(s.length(), l);

		// generate solutions from the end
		for (int i = s.length() - 1; i >= 0; i--) {
			List<String> values = new ArrayList<String>();
			for (int j = i + 1; j <= s.length(); j++) {
				String tmp = s.substring(i, j);
				if (dict.contains(tmp)) {
					for (String word : validMap.get(j)) {
						values.add(tmp + (word.isEmpty() ? "" : " ") + word);
					}
				}
			}
			validMap.put(i, values);
		}
		return validMap.get(0);
	}

	public List<String> wordBreak(String s, Set<String> wordDict) {
		return DFS(s, wordDict, new HashMap<String, List<String>>());
	}

	// DFS function returns an array including all substrings derived from s.
	public List<String> DFS(String s, Set<String> wordDict, HashMap<String, List<String>> map) {
		if (map.containsKey(s))
			return map.get(s);

		List<String> res = new ArrayList<String>();
		if (s.length() == 0) {
			res.add("");
			return res;
		}
		for (String word : wordDict) {
			if (s.startsWith(word)) {
				List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
				for (String sub : sublist)
					res.add(word + (sub.isEmpty() ? "" : " ") + sub);
			}
		}
		map.put(s, res);
		return res;
	}

	/**
	 
	 I've been struggling with this problem for a long time, and I'd love to 
	 share three different strategies I have tried to solve it. All of them are ACed.
	
	Method 1: DP + DFS. Very similar to Word Break I, but instead of using a boolean dp array, 
	I used an array of Lists to maintain all of the valid start positions for every end position. 
	Then just do classic backtracking to find all solutions. The time complexity is O(n*m) + O(n * number of solutions), 
	where n is the length of the input string, m is the length of the longest word in the dictionary. 
	The run time was 6ms. It is very efficient because DP is used to find out all the valid answers, 
	and no time is wasted on doing the backtracking.
	
	public List<String> wordBreak(String s, Set<String> wordDict) {
	  List<Integer>[] starts = new List[s.length() + 1]; // valid start positions
	  starts[0] = new ArrayList<Integer>();
	  
	  int maxLen = getMaxLen(wordDict);
	  for (int i = 1; i <= s.length(); i++) {
	      for (int j = i - 1; j >= i - maxLen && j >= 0; j--) {
	          if (starts[j] == null) continue;
	          String word = s.substring(j, i);
	          if (wordDict.contains(word)) {
	              if (starts[i] == null) {
	                  starts[i] = new ArrayList<Integer>();
	              }
	              starts[i].add(j);
	          }
	      }
	  }
	  
	  List<String> rst = new ArrayList<>();
	  if (starts[s.length()] == null) {
	      return rst;
	  }
	  
	  dfs(rst, "", s, starts, s.length());
	  return rst;
	}
	
	
	private void dfs(List<String> rst, String path, String s, List<Integer>[] starts, int end) {
	  if (end == 0) {
	      rst.add(path.substring(1));
	      return;
	  }
	  
	  for (Integer start: starts[end]) {
	      String word = s.substring(start, end);
	      dfs(rst, " " + word + path, s, starts, start);
	  }
	}
	
	private int getMaxLen(Set<String> wordDict) {
	  int max = 0;
	  for (String s : wordDict) {
	      max = Math.max(max, s.length());
	  }
	  return max;
	}
	
	Method 2: Memoization + Backtracking. Before I came up with Method 1, I also tried using a HashMap to memoize all the possible strings that can be formed starting from index i. I referred to this post from @Pixel_
	The time complexity is O(len(wordDict) ^ len(s / minWordLenInDict)) as @Pixel_ mentioned. The space complexity would be larger than other methods though. Here is my code:
	
	public List<String> wordBreak(String s, Set<String> wordDict) {
	  HashMap<Integer, List<String>> memo = new HashMap<>(); // <Starting index, rst list>
	  return dfs(s, 0, wordDict, memo);
	}
	
	private List<String> dfs(String s, int start, Set<String> dict, HashMap<Integer, List<String>> memo) {
	  if (memo.containsKey(start)) {
	      return memo.get(start);
	  }
	  
	  List<String> rst = new ArrayList<>();
	  if (start == s.length()) {
	      rst.add("");
	      return rst;
	  }
	  
	  String curr = s.substring(start);
	  for (String word: dict) {
	      if (curr.startsWith(word)) {
	          List<String> sublist = dfs(s, start + word.length(), dict, memo);
	          for (String sub : sublist) {
	              rst.add(word + (sub.isEmpty() ? "" : " ") + sub);
	          }
	      }
	  }
	  
	  memo.put(start, rst);
	  return rst;
	}
	Method 3: DP Prunning + Backtracking. My very first solution is like this: using a boolean array to memoize whether a substring starting from position i to the end is breakable. This works well for worst cases like: s = "aaaaaaaaaaaab", dict = ["a", "aa", "aaa", "aaaa"]. However, for cases like: s = "aaaaaaaaaaaaa", dict = ["a", "aa", "aaa", "aaaa"], the time complexity is still O(2^n). Here is the code:
	
	public List<String> wordBreak(String s, Set<String> wordDict) {
	  List<String> rst = new ArrayList<>();
	  if (s == null || s.length() == 0 || wordDict == null) {
	      return rst;
	  }
	  
	  boolean[] canBreak = new boolean[s.length()];
	  Arrays.fill(canBreak, true);
	  StringBuilder sb = new StringBuilder();
	  dfs(rst, sb, s, wordDict, canBreak, 0);
	  return rst;
	}
	
	private void dfs(List<String> rst, StringBuilder sb, String s, Set<String> dict, 
	  boolean[] canBreak, int start) {
	  if (start == s.length()) {
	      rst.add(sb.substring(1));
	      return;
	  }
	  
	  if (!canBreak[start]) {
	      return;
	  }
	  
	  for (int i = start + 1; i <= s.length(); i++) {
	      String word = s.substring(start, i);
	      if (!dict.contains(word)) continue;
	      
	      int sbBeforeAdd = sb.length();
	      sb.append(" " + word);
	      
	      int rstBeforeDFS = rst.size();
	      dfs(rst, sb, s, dict, canBreak, i);
	      if (rst.size() == rstBeforeDFS) {
	          canBreak[i] = false;
	      }
	      sb.delete(sbBeforeAdd, sb.length());
	  }
	}
	
	private int getMaxLen(Set<String> wordDict) {
	  int max = 0;
	  for (String s : wordDict) {
	      max = Math.max(max, s.length());
	  }
	  return max;
	}
	 
	 * 
	 * */

	public static void main(String[] args) {
		String s = "catsanddog";
		L140WordBreakII test = new L140WordBreakII();
		Set<String> dict = new HashSet<String>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
		test.wordBreak_DP(s, dict);

	}

}
