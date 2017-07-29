package multithread.d13_deadlock;


/**
 * Deadlock
 * https://www.youtube.com/watch?v=ghCUBi41bGA&index=11&list=PLBB24CFB073F1048E
 */
public class App {

	public static void main(String[] args) throws InterruptedException {
		final Runner runner = new Runner();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					runner.firstThread();
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
					runner.secondThread();
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
		
		runner.finished();
	}
}
