package edu.umb.cs681.hw0012;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement{
	
	private LinkedList<FSElement> children;

	public Directory(Directory parent, String name) {
		super(parent, name, 0, LocalDateTime.now());
		children = new LinkedList<FSElement>();
		isDir = true;
		isFile = false;
		lock = new ReentrantLock();
	}

	public LinkedList<FSElement> getChildren() {
		
		return this.children;
		
	}
	

	
	public void appendChild (FSElement element) {
		lock.lock();
		try {
			children.add(element);
		} finally {
			lock.unlock();
		}
		
		
	}
	
	
	public int getTotalSize () {
		lock.lock();
		try {
			int totalSize = 0;
			
			for (FSElement element: children){
			
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
