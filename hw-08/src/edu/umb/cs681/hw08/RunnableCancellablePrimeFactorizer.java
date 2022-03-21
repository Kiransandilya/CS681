package edu.umb.cs681.hw08;
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
		long o = from;
		while( q != 1 && o <= to ){
			lock.lock();
			try{
				if(done){
					break;
				}
				if( o > 2 && isEven(o)) {
					o++;
					continue;
				}
				if(q % o == 0) {
					factors.add(o);
					q /= o;
				}else {
					if(o==2){ 
						o++; 
					}
					else{ 
						o += 2; 
					}
				}
			} 
			finally {
				lock.unlock();
			}
		}
	}
}