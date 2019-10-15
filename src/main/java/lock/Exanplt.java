package lock;

import java.util.Random;

public class Exanplt extends AQS {
	private static final long serialVersionUID = 1L;

	protected boolean tryAcquire(int arg) {
		return false;
	}
	
	public static void test() {
		Exanplt instance = new Exanplt();
		for (int ii = 0; ii < 10; ii++) {
			Thread run1 = new Thread(new  Runnable() {
				@Override
				public void run() {
					Thread.currentThread().setName(new Random().nextInt() + "Thread");
					instance.acquire(1);
					try {
						Thread.sleep(100000000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			run1.start();
		}

	}

	public static void main(String[] args) throws Exception {
		Exanplt.test();
	}
}
