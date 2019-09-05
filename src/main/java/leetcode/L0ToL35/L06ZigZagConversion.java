package leetcode.L0ToL35;

/**
 * @author zhailzh
 * 
 * @Date 20151212:28:11
 The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
 
 *  */
public class L06ZigZagConversion {
	
	
	/**

P     I    N
A   L S  I G
Y A   H R
P     I
 
 首先是垂直方向上的，然后是斜上方向的数据，最主要的是初始化了多个StringBuffer
	 * */
	
	public String copy(String p,int rows) {
		if(p == null || p.length() < rows) return p;
		char[] pChar = p.toCharArray();
		int pLen = p.length();
		int i = 0;
		StringBuffer[] sb = new StringBuffer[rows];
		for (StringBuffer stringBuffer : sb) {
			stringBuffer = new StringBuffer();
		}
		while(i < pChar.length) {
			for(int sbindex = 0;sbindex<sb.length && i<pLen;sbindex ++) {
				sb[sbindex].append(pChar[i++]);
			}
			
			for (int sbindex = rows-2; sbindex >= 1 && i<pLen; sbindex--) {
				sb[sbindex].append(pChar[i++]);
			}
		}
		
		for (int j = 1; j < sb.length; j++) {
			sb[0].append(sb[i]);
		}
		return sb[0].toString();
	}
	
	
	public String convert_mul_Buffer(String s, int nRows) {
		
    char[] c = s.toCharArray();
    int len = c.length;
    StringBuffer[] sb = new StringBuffer[nRows];
    for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
   
    int i = 0;
    while (i < len) {
        for (int idx = 0; idx < nRows && i < len; idx++) // vertically down 垂直方向
            sb[idx].append(c[i++]);
        for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up 斜上方向
            sb[idx].append(c[i++]);
    }
    
    for (int idx = 1; idx < sb.length; idx++)
        sb[0].append(sb[idx]);
    return sb[0].toString();
}

	public static String convert(String s, int numRows) {
	    if(numRows<=1) return s;
	    StringBuilder sb = new StringBuilder();
	    int guilushu = 2*(numRows -1);
	    
	    for (int i = 0; i < numRows; i++) {
	    	for (int j = i; j < s.length(); j++) {
	    		if(j%guilushu  == i || j%guilushu == (guilushu - i)){
	    			System.out.print(" "+j);
	    			sb.append(s.charAt(j));
	    		}
			}
	    	System.out.println();
		}
	    return sb.toString();
	}
	
	public String convertbyKongJian(String s, int nRows) {
        if(nRows <= 1)
            return s;
        StringBuilder[] list = new StringBuilder[nRows];
        for(int i = 0; i < nRows; i++)
            list[i] = new StringBuilder();
        int row = 0;
        int i = 0;
        boolean down = true;
        while(i < s.length()){
            list[row].append(s.charAt(i));
            if(row == 0){
                down = true;
            }
            if(row == nRows - 1){
                down = false;
            }
            if(down){
                row++;
            }
            else{
                row--;
            }
            i++;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder sb : list)
            res.append(sb.toString());
        return res.toString();
    }
	
	

//	P   A   H   N
//	A P L S I I G
//	Y   I   R
	
	public static void main(String[] args) {
		System.out.println(convert("PAYPALISHIRING", 3));

	}

}
