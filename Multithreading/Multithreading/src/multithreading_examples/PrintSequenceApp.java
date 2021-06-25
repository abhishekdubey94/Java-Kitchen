package multithreading_examples;

class PrintSequenceRunnable implements Runnable {

	public static final int PRINT_NUMBERS_UPTO = 10;
	static int number = 1;
	int remainder;
	private static Object lock = new Object();

	public PrintSequenceRunnable(int remainder) {
		this.remainder = remainder;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+":Outside while");
		while (number < PRINT_NUMBERS_UPTO-1) {
			System.out.println(Thread.currentThread().getName()+":inside while");
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName()+":Acquired Lock");
				while (number % 3 != remainder) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + " " + number);
				number++;
				lock.notifyAll();
			}
		}
	}
}

public class PrintSequenceApp {

	public static void main(String[] args) {
		Thread t1 = new Thread(new PrintSequenceRunnable(1), "T1");
		Thread t2 = new Thread(new PrintSequenceRunnable(2), "T2");
		Thread t3 = new Thread(new PrintSequenceRunnable(0), "T3");

		t1.start();
		t2.start();
		t3.start();

	}
}
