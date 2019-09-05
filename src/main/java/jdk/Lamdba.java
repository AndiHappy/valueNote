package jdk;

public class Lamdba {
	
	
	
	
	@FunctionalInterface
	interface Converter<F, T> {
	    T convert(F from);
	    
	    boolean equals(Object obj);

	}
	
	

	public static void main(String[] args) {
		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted);    // 123
		
		int num = 1;
		Converter<Integer, String> stringConverter =
		        (from) -> String.valueOf(from + num);
		
		

	}

}
