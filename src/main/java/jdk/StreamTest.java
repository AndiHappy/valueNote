package jdk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * @author guizhai
 *
 */
public class StreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
//
//		myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);
//
//		List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
//
//		long sum = ints.stream().mapToInt(Integer::intValue).sum();;
//		System.out.println(sum);
		
		List<Integer> ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
		System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());
		
		List<List<Integer>> ins = Arrays.asList(
				Arrays.asList(1, 2, 3, 4, 5), 
				Arrays.asList(1, 2, 3, 4, 5), 
				Arrays.asList(1, 2, 3, 4, 5));
		Stream<List<Integer>> paras = ins.stream();
		IntStream value = paras.flatMapToInt(t->{
		 return	t.stream().mapToInt(Integer::intValue);
		});
		
		System.out.println(value.sum());
		
		String poetry = "Where, before me, are the ages that have gone?\n" +
        "And where, behind me, are the coming generations?\n" +
        "I think of heaven and earth, without limit, without end,\n" +
        "And I am all alone and my tears fall down.";
Stream<String> lines = Arrays.stream(poetry.split("\n"));
Stream<String> words = lines.flatMap(line -> Arrays.stream(line.split(" ")));
List<String> l = words.collect(Collectors.toList());
System.out.println(l);
		
	}

}
