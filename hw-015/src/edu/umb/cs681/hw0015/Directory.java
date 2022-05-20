package edu.umb.cs681.hw0015;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unused")
public class Directory extends FSElement{
	
	private ConcurrentLinkedQueue<FSElement> q;

	public Directory(Directory parent, String name) {
		super(parent, name, 0, LocalDateTime.now());
		q = new ConcurrentLinkedQueue<FSElement>();
		isDir = true;
		isFile = false;
		lock = new ReentrantLock();
	}

	public ConcurrentLinkedQueue<FSElement> getChildren() {
		
		return this.q;
		
	}
	

	
	public void appendChild (FSElement element) {
		lock.lock();
		try {
			q.add(element);
		} finally {
			lock.unlock();
		}
		
		
	}
	
	
	public int getTotalSize () {
		lock.lock();
		try {
			int totalSize = 0;
			
			for (FSElement element: q){
			
					totalSize+= element.getSize();
				
			}
			
			return totalSize;
		} finally {
			lock.unlock();
		}
		
	}
	
	public int getSize() {
		return this.size;
	}
    @Override 
	public String toString() {
		return getName();
	}

	@Override
	public boolean isDirectory() {
		return this.isDir;
	}

}
