package valuenotes;
import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class T3 {

	/**
	 * 
	 */
	public T3() {
		// TODO Auto-generated constructor stub
	}

	public int longestNoReapteaed(String v) {
		int[] fullchar = new int[256];
		Arrays.fill(fullchar, -1);
		char[] tmp = v.toCharArray();
		int curf = 0;
		int res = 0;
		for (int i = 0; i < tmp.length; i++) {
			if (fullchar[tmp[i] - 'a'] != -1) {
				curf = Math.max(fullchar[tmp[i] - 'a'], curf);
			}
				res = Math.max(res, i - curf + 1);
				fullchar[tmp[i] - 'a'] = i;
		}
		return res;

	}

	public static void main(String[] args) {
		T3 test = new T3();
		System.out.println(test.longestNoReapteaed("zabcadefg"));

	}

}
