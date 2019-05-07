/**
 * 
 */
package leetcode.L71ToL100;

import java.util.Stack;

/**
 * @author guizhai
 *
 */
public class L71SimplifyPath {

	/**

Given an absolute path for a file (Unix-style), simplify it. 
Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. 
Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix

Note that the returned canonical path must always begin with a slash /, 
and there must be only a single slash / between two directory names. 
The last directory name (if it exists) must not end with a trailing /. 
Also, the canonical path must be the shortest string representing the absolute path.

 

Example 1:

Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
Example 2:

Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
Example 3:

Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
Example 4:

Input: "/a/./b/../../c/"
Output: "/c"
Example 5:

Input: "/a/../../b/../c//.//"
Output: "/c"
Example 6:

Input: "/a//b////c/d//././/.."
Output: "/a/b/c"

	 */
	
	/**
	 * 出现的特殊字符只有 / . .. 这三种 
	 * */
	public String simplifyPath(String path) {
		if (path == null || "".equals(path) || path.charAt(0) != '/') return "";
    
    String[] pathArr = path.split("/");
    
    Stack<String> stack = new Stack<>();
    for(int i = 0; i < pathArr.length; i++) {
        if (pathArr[i].equals("..")) {
            if (!stack.isEmpty()) stack.pop();
        } else if (!pathArr[i].equals(".") && !pathArr[i].equals("")) {
            stack.push(pathArr[i]);
        }
    }
    
    //特殊情况处理
    if (stack.isEmpty()) return "/";
    
    String s = "";
    while(!stack.isEmpty()) {
        s = "/" + stack.pop() + s;
    }
    return s;

 }

 public static void main(String[] args){
     L71SimplifyPath test = new L71SimplifyPath();
     System.out.println(test.simplifyPath("/a//b////c/d//././/.."));
 }

}
