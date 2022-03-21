package edu.umb.cs681.hw05;
import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	public void setDone() {
		lock.lock();
		try {
			done = false;
		} finally {
			lock.unlock();
		}
	}
	public void generatePrimes() {
		for (long i = from; i <= to; i++) {
			lock.lock();
			try {
				if (done) {
					break;
				}
				if (isPrime(i)) {
					this.primes.add(i);
				}
			} finally {
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator RCPGen = new RunnableCancellablePrimeGenerator(1,100);
		Thread thread = new Thread(RCPGen);
		thread.start();
		RCPGen.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		RCPGen.getPrimeNumbers().forEach( (Long prime)-> System.out.println(prime + ", ") );
		
		System.out.println("\n"+ "No.of prime numbers found : " + RCPGen.getPrimeNumbers().size());
	}
}