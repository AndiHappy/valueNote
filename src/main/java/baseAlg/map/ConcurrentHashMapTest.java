package baseAlg.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guizhai
 *
 */
public class ConcurrentHashMapTest {

	public static void main(String[] args) {

		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				map.put("k1", "v1");
			}
		});

		thread.start();

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				map.put("k1", "v1");
			}
		});

		thread1.start();


	}

}
