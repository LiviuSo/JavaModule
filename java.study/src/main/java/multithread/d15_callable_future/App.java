package multithread.d15_callable_future;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable, Future (return values from threads)
 * @author lsoco_user
 * https://www.youtube.com/watch?v=lnbWFV4b7M4&list=PLBB24CFB073F1048E&index=13
 */
public class App {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();

		// do Future<?> and Callable<Void> if you don't want a return value from the thread
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				if(duration > 2000) {
					throw new IOException("Sleeping for too long!");
				}
				
				System.out.println("Starting...");
				
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Finished.");
				return duration;
			}

		});
		
		executor.shutdown();
		
		try {
			System.out.println("The result is: " + future.get()); // get blocks till thread terminates
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(ExecutionException e1) {
			System.out.println(e1.getMessage());
		}
	}

}
