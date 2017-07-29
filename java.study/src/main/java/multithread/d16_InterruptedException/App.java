package multithread.d16_InterruptedException;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * InterruptedException (how to interrupt a thread)
 * 
 * @author lsoco_user
 *         https://www.youtube.com/watch?v=6HydEu75iQI&list=PLBB24CFB073F1048E&index=14
 */
public class App {

	// demo interrupted with a thread pool
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting...");

		ExecutorService exec = Executors.newCachedThreadPool();

		Future<?> fu = exec.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				Random ran = new Random();
				for(int i=0; i< 1E8; i++) {
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted!");
						break;
					}
					Math.sin(ran.nextDouble());
				}
				return null;
			}
		});

		exec.shutdown();
		
		Thread.sleep(500);
		
//		fu.cancel(false); // doest't change anything
//		fu.cancel(true); // it does interrupt
		exec.shutdownNow(); // it does interrupt
		
		exec.awaitTermination(1,  TimeUnit.DAYS);
		
		System.out.println("Finished.");
	}

	// demo interrupted with one thread
//	public static void main(String[] args) throws InterruptedException {
//		System.out.println("Starting...");
//
//		Thread t = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				Random ran = new Random();
//
//				for (int i = 0; i < 1E8; i++) {
//					// try {
//					// Thread.sleep(1);
//					// } catch (InterruptedException e) {
//					// System.out.println("We've been interrupted");
//					// break;
//					// }
//
//					// alternative
//					if (Thread.currentThread().isInterrupted()) {
//						System.out.println("Interrupted!");
//						break;
//					}
//					Math.sin(ran.nextDouble());
//				}
//			}
//		});
//
//		t.start();
//
//		Thread.sleep(500);
//
//		t.interrupt(); // flags the thread (with "interrupted")
//
//		t.join();
//
//		System.out.println("Finished.");
//	}

}
