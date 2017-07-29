package multithread.d08_countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch: A synchronization aid that 
 * allows one or more threads to wait until a set of operations being
 *  performed in other threads completes.
 * @author lsoco_user
 * https://www.youtube.com/watch?v=1H-Vfu1v_2g&list=PLBB24CFB073F1048E&index=6
 */
public class App {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3); 
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(int i = 0; i < 3; i++) {
			executor.submit(new Processor(latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed!");
	}
}


class Processor implements Runnable {

	private CountDownLatch latch;
	
	public Processor(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Started");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
	}
}