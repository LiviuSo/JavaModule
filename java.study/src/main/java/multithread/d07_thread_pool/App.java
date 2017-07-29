package multithread.d07_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Thread pool
 * @author lsoco_user
 * https://www.youtube.com/watch?v=KUdro0G1BV4&list=PLBB24CFB073F1048E&index=5
 */
public class App {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for(int i=0; i<4; i++) {
			executor.submit(new Processor(i));
		}
		executor.shutdown(); // wait for the threads to complete
		System.out.println("All task submitted");
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed");
	}
}

class Processor implements Runnable {

	private int id;
	
	public Processor(int id) {
		this.id = id;
	}
	@Override
	public void run() {
		System.out.println("Starting: " + id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed: " + id);		
	}	
}
