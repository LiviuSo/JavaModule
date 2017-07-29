package multithread.d11_producer_consumer;

/**
 * Consumer/producer with wait(), notify()
 * @author lsoco_user
 * https://www.youtube.com/watch?v=wm1O8EE0X8k&list=PLBB24CFB073F1048E&index=9
 */
public class App {
	
	public static void main(String[] args) throws InterruptedException {
		final Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					processor.produce();
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
					processor.consume();
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
}
