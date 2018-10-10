package baseAlg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author zhailz
 *
 * @version 2018年9月30日 下午3:03:49
 */
public class AlgUtil {
	
	/**
	 * 全排列的算法,分析规律：
	 * 一个字母的时候，就是一个字母a
	 * 当有两个字母的时候，就是在一个字母的前后添加，得到 ab，ba
	 * 三个字母的时候，就是在这两个字母的前后，中间添加，得到：cab,abc,acb,cba,bac,bca
	 * 四个字母的时候，就是三个字母的六个排列中添加了
	 * 
	 * 分析到这里分析出递归的跳出条件和递归调用的循环条件
	 * f(n){
	 * f1 return value.add(char)
	 * fn return 遍历f(n-1)对应的value值+fn
	 * }
	 * 
	 * 还是写不出来代码，继续分析：递归调用，采用最简单的sum计算，来对应递归调用
	public  void sum(int value,List<Integer> res){
		if(value == 1){
			res.add(value);
			return;
		}
		res.add(value);
		sum(value -1,res);
	}	 
    *
    *
    * 对应的递归调用的退出条件，以及递归调用之间的循环条件，非常的明显。我们从这个递归的样本来分析上面的那个递归. 可以写出来一个比较粗糙的递归迪欧调用
    * 
    * 递归退出的条件：f1 value值中添加f1
    * 递归的循环条件：fn f(n-1)的value值，循环其中的value值，寻找fn的可以插入对应的位置中。
    * 
    * 然后理清了头绪之后，不必要使用递归，扩展成非递归的
	* */
	
	public void recursive(char[] value,int from,int to,List<StringBuilder> res){
		
		if(from == to){
			res.add(new StringBuilder().append(value[from]));
			return ;
		}
		recursive(value,from,to-1,res);
		char fc = value[to];
		List<StringBuilder> res1 = new ArrayList<StringBuilder>();
		for (StringBuilder string : res) {
			for (int i = 0; i < string.length(); i++) {
				StringBuilder tmp = new StringBuilder().append(string.toString()).insert(i, fc);
				res1.add(tmp);
			}
			StringBuilder tmp = new StringBuilder().append(string.toString()).append(fc);
			res1.add(tmp);
		}
		res.clear();
		res.addAll(res1);
	}	
	
	
	public Collection<String> wholeArrangement(char[] value){
		ArrayDeque<String> dequeue = new ArrayDeque<String>();
		for (int i = 0; i < value.length; i++) {
			char c = value[i];
			if(dequeue.isEmpty()){
				dequeue.add(c+"");
			}else{
				int size = dequeue.size();
				for (int j = 0; j < size; j++) {
					String last = dequeue.pollLast();
					for (int k = 0; k < last.length(); k++) {
						StringBuilder tmp = new StringBuilder().append(last).insert(k, c);
						dequeue.push(tmp.toString());
					}
					StringBuilder tmp = new StringBuilder().append(last).append(c);
					dequeue.push(tmp.toString());

				}
			}
			
		}
		return dequeue;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AlgUtil al = new AlgUtil();
		List<StringBuilder> res = new ArrayList<StringBuilder>();
		al.recursive(new char[]{'a','b','c','d'},0 , 3, res);
		System.out.println("size:{} "+ res.size() + " value: " + res.toString());

		Collection<String> value = al.wholeArrangement(new char[]{'a','b','c','d'});
		System.out.println("size:{} "+ value.size() + " value: " + value.toString());

	}

}
