package multithread.d14_semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore
 * @author lsoco_user
 * https://www.youtube.com/watch?v=KxTRsvgqoVQ&index=12&list=PLBB24CFB073F1048E
 */
public class App {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i = 0; i<200; i++) {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					Connection.getInstance().connect();
				}
			});
		}
		
		executor.shutdown();
		executor.awaitTermination(1,  TimeUnit.DAYS);
	}
}
