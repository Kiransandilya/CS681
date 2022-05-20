package edu.umb.cs681.hw0011;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	private static Map<Path, Integer> accessCountMap = new HashMap<>();
	private ReentrantLock lock = new ReentrantLock(); 

	public void increment(Path path){
		lock.lock();
		try{
			if(accessCountMap.containsKey(path))
				accessCountMap.put(path,accessCountMap.get(path)+1);
			else
				{accessCountMap.put(path,1);}
		} finally {
			lock.unlock();
		}
	}

	public int getCount(Path path){
		lock.lock();
		int c=0;
		try{
			if(accessCountMap.containsKey(path))
				c= accessCountMap.get(path);
		} finally {
			lock.unlock();
		}
		return c;
	}
	
	private static AccessCounter instance = null;
	private static ReentrantLock lock1 = new ReentrantLock();
	public static AccessCounter getInstance(){
		lock1.lock();
		try{
			if(instance==null){ instance = new AccessCounter(); }
			return instance;
		}finally{
			lock1.unlock();
		}
	}



}

