package multithreading_examples;

public class PrintOddEvenApp {

	public static int counter = 1;
	public static int limit = 10;

	public static void main(String[] args) {
		Object lock = new Object();
		Thread t1 = new Thread(new OddThread(lock), "t1");
		Thread t2 = new Thread(new EvenThread(lock), "t2");

		t1.start();
		t2.start();

	}

}

class OddThread implements Runnable {

	private Object lock;

	public OddThread(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {

		while (PrintOddEvenApp.counter <= PrintOddEvenApp.limit) {
			synchronized (lock) {
				if(PrintOddEvenApp.counter%2==0) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName()+":"+PrintOddEvenApp.counter);
				PrintOddEvenApp.counter++;
				lock.notifyAll();
			}
		}

	}

}

class EvenThread implements Runnable {

	private Object lock;

	public EvenThread(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		while (PrintOddEvenApp.counter <= PrintOddEvenApp.limit) {
			synchronized (lock) {
				if(PrintOddEvenApp.counter%2==1) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName()+":"+PrintOddEvenApp.counter);
				PrintOddEvenApp.counter++;
				lock.notifyAll();
			}
		}

	}

}
