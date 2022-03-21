package edu.umb.cs681.hw08;
import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellableInterruptiblePrimeFactorizer extends
RunnableCancellablePrimeFactorizer {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	public RunnableCancellableInterruptiblePrimeFactorizer(long q, long from, long to) {
		super(q, from, to);
	}
	public void setDone() {
		lock.lock();
		try {
			done = true;
		} 
		finally {
			lock.unlock();
		}
	}
	public void generatePrimeFactors() {
		long n = from;
		while (q != 1 && n <= to) {
			lock.lock();
			try {
				if (done) {
					System.out.println("Factors generation stopped");
					this.factors.clear();
					break;
				}
				if (n > 2 && isEven(n)) {
					n++;
					continue;
				}
				if (q % n == 0) {
					factors.add(n);
					q /= n;
				} else {
					if (n == 2) {
						n++;
					} else {
						n += 2;
					}
				}
			} 
			finally {
				lock.unlock();
			}
			try {
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				continue;
			}
		}
	}
	public static void main(String[] args) {
		RunnableCancellableInterruptiblePrimeFactorizer RCIPF_obj = new
		RunnableCancellableInterruptiblePrimeFactorizer(9,2, 100);
		Thread t = new Thread(RCIPF_obj);
		t.start();
		try {
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		RCIPF_obj.setDone();
		t.interrupt();
		try {
			t.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n" + "Generated Prime Factors for the given qidends are: " + RCIPF_obj.getPrimeFactors());
	}
}