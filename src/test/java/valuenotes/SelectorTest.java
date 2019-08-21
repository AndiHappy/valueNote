package valuenotes;

import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author guizhai
 *
 */
public class SelectorTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SelectorProvider provider = SelectorProvider.provider();
		
	  AbstractSelector opener = 	provider.openSelector();
	  
	  AbstractSelector opener1 = 	provider.openSelector();

	  System.out.println(opener);
	  
	  System.out.println(opener1);

	}

}
