package edu.umb.cs681.hw07;
import java.util.concurrent.locks.ReentrantLock;
public class ConcurrentSingleton {
	private static ConcurrentSingleton cs_obj = null;
	private static ReentrantLock lock = new ReentrantLock();
	public static ConcurrentSingleton getInstance(){
		lock.lock();
		try {
			if (cs_obj == null) {
				cs_obj = new ConcurrentSingleton();
			}
			return cs_obj; 
		}
		finally {
				lock.unlock();
		}
	}
}