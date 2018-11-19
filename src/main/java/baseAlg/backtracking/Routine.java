package baseAlg.backtracking;

/**
 * @author zhailz
 * 2018年11月19日 下午10:21:29
 */
public class Routine {
	
	public  void alg(){
		alg(new String[]{"a","b","c","d"},0,4);
	}
	
	private void alg(String[] strings ,int i, int j) {
		for (int j2 = 0; j2 < j && i < j; j2++) {
			String string = strings[j2];
			System.out.print(" "+ string);
			alg(strings, i+1, j);
			System.out.println();
		}
	}

	public static void main(String[] args){
		Routine test = new Routine();
		test.alg();
	}

}
