package leetcode;

/**
 * @author zhailzh
 * 
 * @Date 201511162:17:16
 * 
 * 
 * 
 */
public class L05LongestPalindromicSubstring {

	public static void main(String[] args) {
		String value = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
		L05LongestPalindromicSubstring lo = new L05LongestPalindromicSubstring();
		System.out.println(lo.longestPalindrome(value));
		System.out.println(lo.longestPalindrome1("abavalueeula"));

	}
	
	
	public String longestPalindrome(String s) {
		  int n = s.length();
		  String res = null;

		  boolean[][] dp = new boolean[n][n];

		  for (int i = n - 1; i >= 0; i--) {
		    for (int j = i; j < n; j++) {
		      dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

		      if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
		        res = s.substring(i, j + 1);
		      }
		    }
		  }

		  return res;
		}
	
	/**
	 * 
	 * */
	public String longestPalindrome1(String s) {
		if(s == null || s.length() < 2){
			return s;
		}
		  int n = s.length();
		  int max = 0;
		  int right =0;
		  int left = 0;
		  boolean[][] dp = new boolean[n][n];

		  for(int j = 1;j< n;j++){
			  for (int i = 0; i < j; i++) {
				  boolean isInnerWordPalindrom = dp[i+1][j-1] || (j-i <=2);
				  if(s.charAt(i) == s.charAt(j) && isInnerWordPalindrom){
					  dp[i][j] = true;
					  if((j -i + 1) > max){
						  max = j-i+1;
						  left = i;
						  right = j;
					  }
				  }
			}
		  }
		  return s.substring(left, right+1);
		}
	
	public String longestPalindrome2(String s) {
		if(s == null || s.length() < 2){
			return s;
		}
		  int n = s.length();
		  int max = 0;
		  int right =0;
		  int left = 0;
		  boolean[][] dp = new boolean[n][n];

		  for(int j = 1;j< n;j++){
			  for (int i = 0; i < j; i++) {
				  if((s.charAt(i) == s.charAt(j) &&  dp[i+1][j-1] ) || (s.charAt(i) == s.charAt(j) && (j-i <=2))){
					  dp[i][j] = true;
					  if((j -i + 1) > max){
						  max = j-i+1;
						  left = i;
						  right = j;
					  }
				  }
			}
		  }
		  return s.substring(left, right+1);
		}

	@Deprecated
	public String longestPalindrome_isWrong(String s) {
		int temp = 0;int tempi = 0; int tempj = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				if(isPalind(s,i,j)){
					if(temp < (j-i+1)){
						temp = j-i+1;
						tempi =i;
						tempj =j;
					}
				}
			}
		}
		if(temp != 0){
			return s.substring(tempi, tempj);
		}
		return null;
	}
	
	
	public boolean isPalind(String value,int start ,int end){
	
		if(!value.isEmpty()){
			while(start <= end){
			 if(value.charAt(start) == value.charAt(end)){
				 start++;
				 end--;
			 }else{
				 return false;
			 }	
			}	
		}
		return true;
	}

}
