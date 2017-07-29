package multithread.d10_wait_notify;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("[Producer] Thread running ...");
			wait(); // resource optimized; relinquishes the lock
			System.out.println("[Producer] Resumed.");
		}
	}

	public void consume() throws InterruptedException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized (this) {
			System.out.println("[Consumer] Waiting for return key ...");
			scanner.nextLine();
			System.out.println("[Consumer] Return key pressed");
			notify(); // notify the thread that waiting on the lock that it can resume
			System.out.println("[Consumer] Executing the rest of the code after notify()");
			Thread.sleep(5000);	// however the other thread won't resume, until this one ends 
		}
	}

}
