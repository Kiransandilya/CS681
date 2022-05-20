package edu.umb.cs681.hw0014;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	final ConcurrentMap<Path, AtomicLong> counter = new ConcurrentHashMap<>();
	private ReentrantLock rLock = new ReentrantLock();  

	public void increment(Path path){
		rLock.lock();
		try{
			counter.computeIfAbsent(path, p -> new AtomicLong()).incrementAndGet();
		} finally {
			rLock.unlock();
		}
	}

	public long getCount(Path path){
		rLock.lock();
		try{
			AtomicLong l = counter.get(path);
		    return l == null ? 0 : l.get();
		} finally {
			rLock.unlock();
		}
	}
	
	private static AccessCounter ac_instance = null;
	private static ReentrantLock rLock1 = new ReentrantLock();
	public static AccessCounter getInstance(){
		rLock1.lock();
		try{
			if(ac_instance==null){ ac_instance = new AccessCounter(); }
			return ac_instance;
		}finally{
			rLock1.unlock();
		}
	}



}

