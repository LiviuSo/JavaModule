package multithread.d09_blocking_queue;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Blocking queues, producer-consumer
 * @author lsoco_user
 * https://www.youtube.com/watch?v=Vrt5LqpH2D0&list=PLBB24CFB073F1048E&index=7
 */
public class App {
	
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10); // FIFO

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
	
	private static void producer() throws InterruptedException {
		Random random = new Random();
		while(true) {
			Thread.sleep(random.nextInt(100)); // simulate variable processing time
			queue.put(random.nextInt(100));
		} 
	}
	
	private static void consumer() throws InterruptedException {
		Random random = new Random();
		while(true) {
			Thread.sleep(random.nextInt(100)); // simulate variable processing time
			Integer value = queue.take();
			System.out.print("Take value: " + value + "; Queue size is: " + queue.size());
			printElems();
		}
	}
	
	private static void printElems() {
		System.out.print(" Queue: ");
		Iterator<Integer> it = queue.iterator();
		while(it.hasNext()) {
			System.out.print(" " + it.next());
		}
		System.out.println();
	}
}

