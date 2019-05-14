package leetcode.L91ToL105;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L93RestoreIPAddresses {

	/**
	Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
	 */
	
	//第一个位置的数字最长的长度为3，最短为1，
	public static  List<String> restoreIpAddresses(String s) {
    List<String> res = new ArrayList<String>();
    int len = s.length();
//    第一个位置的数字最长的长度为3，最短为1
    for(int i = 1; i<4 && i<len-2; i++){
        for(int j = i+1; j<i+4 && j<len-1; j++){
        	// 判断最后，必须留下一个位置
            for(int k = j+1; k<j+4 && k<len; k++){
                String s1 = s.substring(0,i), s2 = s.substring(i,j), s3 = s.substring(j,k), s4 = s.substring(k,len);
                if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)){
                    res.add(s1+"."+s2+"."+s3+"."+s4);
                }
            }
        }
    }
    return res;
}
public static  boolean isValid(String s){
    if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
        return false;
    return true;
}
	 
	public static void main(String[] args) {
		System.out.println(restoreIpAddresses("25525511135"));

	}

}
