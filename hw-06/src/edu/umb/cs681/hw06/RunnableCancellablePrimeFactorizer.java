package edu.umb.cs681.hw06;
import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	public RunnableCancellablePrimeFactorizer (long q, long from, long to){
		super(q, from, to);
	}
	public void setDone() {
		lock.lock();
		try {
			done = false;
		}
		finally {
			lock.unlock();
		}
	}
	public void generatePrimeFactors(){
		long n = from;
		while( q != 1 && n <= to ){
			lock.lock();
			try{
				if(done){
					break;
				}
				if( q > 2 && isEven(n)) {
					n++;
					continue;
				}
				if(q % n == 0) {
					factors.add(n);
					q /= n;
				}else {
					if(n==2){ 
						n++; 
					}
					else{
						n += 2;
					}
				}
			} finally {
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) {
		RunnableCancellablePrimeFactorizer RCPF_obj = new RunnableCancellablePrimeFactorizer(963, 2,(long) Math.sqrt(963));
		Thread thread = new Thread(RCPF_obj);
		thread.start();
		RCPF_obj.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Prime factors for the given value are: " + RCPF_obj.getPrimeFactors() + "\n");
	}
}