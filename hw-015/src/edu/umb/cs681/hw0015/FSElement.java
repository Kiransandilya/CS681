package edu.umb.cs681.hw0015;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
	
	private String name;
	private LocalDateTime created; 
	protected int size;
	
	private Directory parent;
	protected boolean isFile;
	protected boolean isDir;
	
	protected ReentrantLock lock; 
	
	
	public FSElement (Directory parent, String name, int size, LocalDateTime created){
		this.parent = parent;
		this.name = name;
		this.size = size;
		this.created = created;
		
	}
	
	public Directory getParent () {
		return this.parent;
	}
	
	public boolean isFile (){
		return this.isFile;
		
	}
	
	public abstract boolean isDirectory();
	
	public void setName (String name){
		this.name = name;
	}
	
	public String getName (){
		return this.name;
	}
	
	
	
	public int getSize () {
		return this.size;
	}

	public LocalDateTime getCreated() {
		return created;
	}



}
