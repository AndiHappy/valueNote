package jdk;

import com.google.common.base.Function;

class Lambda4 {
	
	@FunctionalInterface
	interface Converter<F, T> {
	    T convert(F from);
	    
	    boolean equals(Object obj);

	}
	
    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };
        System.out.println(stringConverter1.convert(23));

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
        System.out.println(stringConverter2.convert(230));
    }
    
    public static void main(String[] args) {
    	Lambda4 converter = new Lambda4();
    	converter.testScopes();
    	Function<String, Integer> toInteger = Integer::valueOf;

  	}
}