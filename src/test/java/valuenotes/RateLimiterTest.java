package valuenotes;

/**
 * @author guizhai
 *
 */
public class RateLimiterTest {

	/**
	 * 
	 */
	public RateLimiterTest() {
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
			RateLimiter value = RateLimiter.create(2);
			for (int i = 0; i < 1000; i++) {
				boolean res = value.tryAcquire();
				System.out.println(res);
				Thread.sleep(100);
			}

	}

}
